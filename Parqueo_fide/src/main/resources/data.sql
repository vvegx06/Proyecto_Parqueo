-- ROLES
INSERT INTO rol (nombre) VALUES 
  ('ADMIN'),
  ('CLIENTE');

-- USUARIOS (tabla: usuarios)
INSERT INTO usuarios (nombre, username, password, correo, rol_id) VALUES
  ('Administrador', 'admin', '{noop}admin123', 'admin@fidelitas.cr', 1),
  ('Carlos Pérez', 'carlos', '{noop}1234', 'carlos@fidelitas.cr', 2),
  ('María López', 'maria', '{noop}abcd', 'maria@fidelitas.cr', 2);

-- ZONAS (tabla: zonas_parqueo)
INSERT INTO zonas_parqueo (nombre, tipo, capacidad_total) VALUES
  ('Zona A', 'ABIERTO', 20),
  ('Zona B', 'ABIERTO', 20),
  ('Zona Discapacitados', 'DISCAPACITADOS', 5);

-- ESPACIOS (tabla: espacio)
-- Zona A
INSERT INTO espacio (codigo, zona, disponible, descripcion) VALUES
  ('A1', 'Zona A', true, 'Cerca de la entrada'),
  ('A2', 'Zona A', true, 'Sombra parcial'),
  ('A3', 'Zona A', true, 'Espacio estándar'),
  ('A4', 'Zona A', true, 'Espacio estándar'),
  ('A5', 'Zona A', true, 'Espacio estándar');

-- Zona B
INSERT INTO espacio (codigo, zona, disponible, descripcion) VALUES
  ('B1', 'Zona B', true, 'Cerca de caseta'),
  ('B2', 'Zona B', true, 'Espacio estándar'),
  ('B3', 'Zona B', true, 'Espacio estándar'),
  ('B4', 'Zona B', true, 'Espacio estándar'),
  ('B5', 'Zona B', true, 'Espacio estándar');

-- Zona Discapacitados
INSERT INTO espacio (codigo, zona, disponible, descripcion) VALUES
  ('D1', 'Zona Discapacitados', true, 'Ancho y señalizado'),
  ('D2', 'Zona Discapacitados', true, 'Ancho y señalizado'),
  ('D3', 'Zona Discapacitados', true, 'Ancho y señalizado');