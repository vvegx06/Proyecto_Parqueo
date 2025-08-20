-- Usuarios de ejemplo
INSERT INTO usuario (id, nombre, correo, contrasena, rol)
VALUES (1, 'Admin', 'admin@fidelitas.cr', 'admin123', 'ADMIN'),
       (2, 'Carlos Pérez', 'carlos@fidelitas.cr', '1234', 'CLIENTE');

-- Parqueos de ejemplo
INSERT INTO parqueo (id, nombre, ubicacion, capacidad)
VALUES (1, 'Parqueo Central', 'San José', 50),
       (2, 'Parqueo Sede Heredia', 'Heredia', 30);

-- Reservas de ejemplo
INSERT INTO reserva (id, fecha, usuario_id, parqueo_id)
VALUES (1, '2025-08-20 08:00:00', 2, 1);

-- Espacios del Parqueo Central (id_parqueo = 1)
INSERT INTO espacio (id, codigo, disponible, parqueo_id)
VALUES 
  (1, 'A1', true, 1),
  (2, 'A2', true, 1),
  (3, 'A3', true, 1),
  (4, 'B1', true, 1),
  (5, 'B2', true, 1);

-- Espacios del Parqueo Heredia (id_parqueo = 2)
INSERT INTO espacio (id, codigo, disponible, parqueo_id)
VALUES 
  (6, 'A1', true, 2),
  (7, 'A2', true, 2),
  (8, 'B1', true, 2);