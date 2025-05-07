# SwiftCart Order Service - Database Schema

## Entities and Attributes

### 1. Order (`orders`)

Represents a customer order, capturing details like customer information, total amount, status, and shipping details.

**Table Name**: `orders`

**Columns**:

| Column Name         | Data Type         | Constraints                     | Description                                                                 |
|---------------------|-------------------|---------------------------------|-----------------------------------------------------------------------------|
| `order_id`          | BIGINT            | PRIMARY KEY, AUTO_INCREMENT     | Unique identifier for the order.                                            |
| `customer_id`       | VARCHAR(50)       | NOT NULL                        | Identifier of the customer (from User Service).                             |
| `order_date`        | TIMESTAMP         | NOT NULL                        | Timestamp when the order was created.                                       |
| `total_amount`      | DECIMAL(10,2)     | NOT NULL                        | Total order amount (e.g., 99.99).                                           |
| `status`            | VARCHAR(20)       | NOT NULL                        | Order status (e.g., PENDING, CONFIRMED, CANCELLED, SHIPPED).                |
| `shipping_address`  | TEXT              | NOT NULL                        | Shipping address (e.g., JSON or formatted string).                          |
| `payment_status`    | VARCHAR(20)       | DEFAULT 'PENDING'               | Payment status (e.g., PENDING, APPROVED, FAILED).                           |
| `created_at`        | TIMESTAMP         | NOT NULL, DEFAULT CURRENT_TIMESTAMP | Timestamp when the order was created (for auditing).                    |
| `updated_at`        | TIMESTAMP         | NOT NULL, DEFAULT CURRENT_TIMESTAMP | Timestamp when the order was last updated (for auditing).                |

### 2. OrderItem (`order_items`)

Represents individual items within an order, linking to products and capturing quantities and prices.

**Table Name**: `order_items`

**Columns**:

| Column Name        | Data Type         | Constraints                     | Description                                                                 |
|--------------------|-------------------|---------------------------------|-----------------------------------------------------------------------------|
| `order_item_id`    | BIGINT            | PRIMARY KEY, AUTO_INCREMENT     | Unique identifier for the order item.                                       |
| `order_id`         | BIGINT            | FOREIGN KEY, NOT NULL           | References the `orders` table (links to the parent order).                  |
| `product_id`       | VARCHAR(50)       | NOT NULL                        | Identifier of the product (from Inventory Service).                         |
| `quantity`         | INTEGER           | NOT NULL                        | Number of units ordered.                                                   |
| `unit_price`       | DECIMAL(10,2)     | NOT NULL                        | Price per unit at the time of order (for immutability).                    |
| `subtotal`         | DECIMAL(10,2)     | NOT NULL                        | Calculated as `quantity * unit_price`.                                     |

### 3. OrderEventLog (`order_event_log`)

Persists events (e.g., `ORDER_CREATED`, `ORDER_CANCELLED`) for reliability, auditability, and integration with Kafka using the transactional outbox pattern.

**Table Name**: `order_event_log`

**Columns**:

| Column Name        | Data Type         | Constraints                     | Description                                                                 |
|--------------------|-------------------|---------------------------------|-----------------------------------------------------------------------------|
| `event_id`         | BIGINT            | PRIMARY KEY, AUTO_INCREMENT     | Unique identifier for the event log entry.                                  |
| `order_id`         | BIGINT            | FOREIGN KEY, NOT NULL           | References the `orders` table (links to the associated order).              |
| `event_type`       | VARCHAR(50)       | NOT NULL                        | Type of event (e.g., ORDER_CREATED, ORDER_CANCELLED).                       |
| `event_data`       | JSONB             | NOT NULL                        | JSON payload of the event (e.g., `{"orderId": 123, "customerId": "C456"}`). |
| `timestamp`        | TIMESTAMP         | NOT NULL                        | Timestamp when the event was logged.                                        |
| `sent`             | BOOLEAN           | DEFAULT FALSE                   | Indicates if the event was published to Kafka.                              |

## Relationships
- **Order to OrderItem**: One-to-Many. One **Order** can have multiple **OrderItem**s, linked via the `order_id` foreign key in `order_items`. Implemented with `@OneToMany` in **Order** and `@ManyToOne` in **OrderItem**.
- **Order to OrderEventLog**: One-to-Many. One **Order** can have multiple **OrderEventLog** entries, linked via the `order_id` foreign key in `order_event_log`. Typically not mapped as a JPA relationship in **Order** to keep it lightweight.
- **OrderItem to OrderEventLog**: No direct relationship. **OrderItem** data may be included in the `event_data` JSONB field of **OrderEventLog** for event payloads.
