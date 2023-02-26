INSERT INTO cargos
    (active, fecha_actualizacion, fecha_creacion, descripcion, nombre)
VALUES
    (true, null, now(), 'Perfil con todos los permisos del sistema', 'Super Administrador');


INSERT INTO permisos
    (active, fecha_actualizacion, fecha_creacion, descripcion, nombre)
VALUES
    (true, null, now(), 'Permite ver el modulo de usuarios', 'VIEW_USERS')
    (true, null, now(), 'Permite Crear Usuarios', 'CREATE_USERS');


INSERT INTO cargos_permisos
    (active, fecha_actualizacion, fecha_creacion, cargo_id, permiso_id)
VALUES
    (true, null, now(), 1, 1),
    (true, null, now(), 1, 2);