


INSERT INTO cliente (id, contrasenia, dni, domicilio, email, estado, nombre, cuenta_id) VALUES ('1', '213qeaq1', '30123123', 'España 900', 'moreno@gmail.com', 'Habilitado', 'Miguel Moreno', '1');
INSERT INTO cliente (id, contrasenia, dni, domicilio, email, estado, nombre, cuenta_id) VALUES ('2', '123qafq1', '27000123', 'San Cayetano 124', 'mary@gmail.com', 'Habilitado', 'Maria Rodriguez', '2');
INSERT INTO cliente (id, contrasenia, dni, domicilio, email, estado, nombre, titular_id) VALUES ('3', '431asf1g', '40000000', 'San Cayetano 124', 'pperez@gmail.com', 'Habilitado', 'Pedro Perez', '2');
INSERT INTO cliente (id, contrasenia, dni, domicilio, email, estado, nombre, cuenta_id) VALUES ('4', '45asd12f', '32000123', 'Argentina 800', 'jcruz@gmail.com', 'Inhabilitado', 'Juan Cruz', '3');

INSERT INTO cuenta (id, estado, fecha_ingreso, limite_extraccion, numero, saldo_actual) VALUES ('1', 'Habilitado', '2010-01-22', '5000', '123456', '10000');
INSERT INTO cuenta (id, estado, fecha_ingreso, limite_extraccion, numero, saldo_actual) VALUES ('2', 'Habilitado', '2014-05-25', '7000', '654321', '15000');
INSERT INTO cuenta (id, estado, fecha_ingreso, limite_extraccion, numero, saldo_actual) VALUES ('3', 'Inhabilitado', '2020-05-15', '8000', '544312', '12000');


INSERT INTO movimiento (id, fecha, hora, importe, tipo, cliente_id, cuenta_id) VALUES ('1', '2020-01-29', '18:04:12', '3000', 'Deposito', '1', '1');
INSERT INTO movimiento (id, fecha, hora, importe, tipo, cliente_id, cuenta_id) VALUES ('2', '2020-01-30', '01:03:33', '2000', 'Extraccion', '1', '1');
INSERT INTO movimiento (id, fecha, hora, importe, tipo, cliente_id, cuenta_id) VALUES ('3', '2020-02-01', '11:23:45', '3000', 'Deposito', '1', '1');
INSERT INTO movimiento (id, fecha, hora, importe, tipo, cliente_id, cuenta_id) VALUES ('4', '2020-02-04', '20:00:21', '5000', 'Deposito', '1', '1');
INSERT INTO movimiento (id, fecha, hora, importe, tipo, cliente_id, cuenta_id) VALUES ('5', '2020-02-05', '17:43:33', '2000', 'Extraccion', '1', '1');
INSERT INTO movimiento (id, fecha, hora, importe, tipo, cliente_id, cuenta_id) VALUES ('6', '2020-02-05', '07:33:12', '3000', 'Extraccion', '1', '1');
INSERT INTO movimiento (id, fecha, hora, importe, tipo, cliente_id, cuenta_id) VALUES ('7', '2020-02-15', '09:33:15', '2000', 'Deposito', '1', '1');
INSERT INTO movimiento (id, fecha, hora, importe, tipo, cliente_id, cuenta_id) VALUES ('8', '2020-02-14', '10:55:31', '1200', 'Extraccion', '1', '1');
INSERT INTO movimiento (id, fecha, hora, importe, tipo, cliente_id, cuenta_id) VALUES ('9', '2020-03-01', '18:55:20', '800', 'Extraccion', '1', '1');
INSERT INTO movimiento (id, fecha, hora, importe, tipo, cliente_id, cuenta_id) VALUES ('10', '2020-03-02', '18:42:20', '500', 'Extraccion', '1', '1');
INSERT INTO movimiento (id, fecha, hora, importe, tipo, cliente_id, cuenta_id) VALUES ('11', '2020-03-14', '22:34:21', '5000', 'Deposito', '1', '1');
INSERT INTO movimiento (id, fecha, hora, importe, tipo, cliente_id, cuenta_id) VALUES ('12', '2020-03-15', '17:44:22', '2000', 'Extraccion', '1', '1');
INSERT INTO movimiento (id, fecha, hora, importe, tipo, cliente_id, cuenta_id) VALUES ('13', '2020-04-15', '18:03:22', '5000', 'Deposito', '1', '1');
INSERT INTO movimiento (id, fecha, hora, importe, tipo, cliente_id, cuenta_id) VALUES ('14', '2020-04-18', '21:09:02', '3000', 'Extraccion', '1', '1');
INSERT INTO movimiento (id, fecha, hora, importe, tipo, cliente_id, cuenta_id) VALUES ('15', '2020-04-27', '02:33:22', '2100', 'Extraccion', '1', '1');
INSERT INTO movimiento (id, fecha, hora, importe, tipo, cliente_id, cuenta_id) VALUES ('16', '2020-05-03', '11:33:55', '1000', 'Deposito', '1', '1');
INSERT INTO movimiento (id, fecha, hora, importe, tipo, cliente_id, cuenta_id) VALUES ('17', '2020-05-05', '11:53:44', '2000', 'Extraccion', '1', '1');
INSERT INTO movimiento (id, fecha, hora, importe, tipo, cliente_id, cuenta_id) VALUES ('18', '2020-05-27', '21:43:14', '10000', 'Deposito', '1', '1');
INSERT INTO movimiento (id, fecha, hora, importe, tipo, cliente_id, cuenta_id) VALUES ('19', '2020-06-04', '15:13:55', '5000', 'Extraccion', '1', '1');
INSERT INTO movimiento (id, fecha, hora, importe, tipo, cliente_id, cuenta_id) VALUES ('20', '2020-06-22', '11:42:22', '2000', 'Deposito', '1', '1');
INSERT INTO movimiento (id, fecha, hora, importe, tipo, cliente_id, cuenta_id) VALUES ('21', '2020-07-21', '12:44:12', '1000', 'Extraccion', '1', '1');
INSERT INTO movimiento (id, fecha, hora, importe, tipo, cliente_id, cuenta_id) VALUES ('22', '2020-08-22', '17:15:18', '2000', 'Deposito', '1', '1');
