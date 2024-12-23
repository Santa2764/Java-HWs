INSERT INTO log_data (level, src, message)
VALUES
    ('INFO', 'Application', 'Application started successfully.'),
    ('DEBUG', 'Service', 'Debugging service initialization.'),
    ('ERROR', 'Database', 'Failed to connect to the database.'),
    ('INFO', 'User', 'User logged in successfully.'),
    ('DEBUG', 'Auth', 'User authentication process started.'),
    ('ERROR', 'Payment', 'Payment processing failed due to insufficient funds.'),
    ('INFO', 'Order', 'Order placed successfully.'),
    ('DEBUG', 'Notification', 'Notification service is running.'),
    ('ERROR', 'API', 'API request failed with status 500.'),
    ('INFO', 'Shutdown', 'Application shutting down gracefully.');