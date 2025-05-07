**Order Service**

#### **Customer can \-**

1. Create Order  
2. Check Order Status  
3. Check order history  
4. Modify existing Order if not shipped  
5. Cancel existing Order if not shipped

#### **Internal Customer Support can \-**

1. Check Order Status  
2. Modify existing Order if not shipped  
3. Check Cancel existing Order if not shipped

#### **Functionalities of the Order Service**

1. **Order Creation**: Create orders, validate stock, reserve inventory, and publish ORDER\_CREATED events.  
2. **Order Status Tracking**: Update and query order status based on events (e.g., PAYMENT\_APPROVED).  
3. **Order Cancellation**: Cancel orders, release stock, and publish ORDER\_CANCELLED events.  
4. **Order Retrieval**: Retrieve order details or customer order history.  
5. **Event Emission**: Log and publish events to Kafka for downstream services.  
6. **Order Validation**: Validate customer, stock, and address in real-time.  
7. **Fault Tolerance**: Ensure atomic writes, retries, and saga-based recovery.  
8. **Auditability**: Maintain event logs for debugging and compliance.  
9. **Order Updates**: Update order details (e.g., address) before shipping.  
10. **Metrics/Reporting**: Provide order data for analytics.  
11. **Notifications**: Trigger customer notifications via events.  
    

#### **Public REST API Endpoints**

1. **POST /api/v1/orders**: Create a new order.  
2. **GET /api/v1/orders/{orderId}**: Get details of a specific order.  
3. **GET /api/v1/orders?customerId={customerId}**: Get a customer’s order history (paginated).  
4. **POST /api/v1/orders/{orderId}/cancel**: Cancel an order.  
5. **PATCH /api/v1/orders/{orderId}**: Update order details (e.g., shipping address).