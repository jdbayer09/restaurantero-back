INSERT INTO cargos
    (id, active, fecha_actualizacion, fecha_creacion, descripcion, nombre)
VALUES
    (1, true, null, now(), 'Perfil con todos los permisos del sistema', 'Super Administrador');

INSERT INTO permisos
    (id, active, fecha_actualizacion, fecha_creacion, descripcion, is_menu, nombre, menu_id)
VALUES
    (1, true, null, NOW(), 'Permite loguearse desde la plataforma WEB', false, 'LOGIN_WEB', null),
    (2, true, null, NOW(), 'Permite Loguearse desde la APP movil', false, 'LOGIN_APP', null);

INSERT INTO cargos_permisos
    (active, fecha_actualizacion, fecha_creacion, cargo_id, permiso_id)
VALUES
    (true, null, now(), 1, 1),
    (true, null, now(), 1, 2);

INSERT INTO usuarios
    (id,active, fecha_actualizacion, fecha_creacion, apellidos, contrasena, nombres, usuario, cargo_id)
VALUES
    (1, true, null, NOW(), 'Administrado', '$2a$10$ZWZQjQKUs4F5iGGsmT3ZGud/lhz22OrdxEI19L64rLnhzIcsmTdtm', 'Super', 'admin', 1);