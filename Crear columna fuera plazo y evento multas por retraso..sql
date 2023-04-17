ALTER TABLE alquiler ADD fuera_plazo TINYINT DEFAULT 0;

drop event if exists eventoMultas;
DELIMITER //
CREATE EVENT IF NOT EXISTS eventoMultas
ON SCHEDULE
EVERY 5 SECOND
STARTS CURRENT_TIMESTAMP
DO BEGIN

  -- Accion 1
  INSERT INTO multa (fk_alquiler, fecha, descartada, importe, observaciones)
  SELECT a.id, CURDATE(), 0, 5, "Entrega Tardia Automatica"
  FROM alquiler a
  WHERE a.fecha_limite < CURDATE()  and a.fecha_entrega is null and a.fuera_plazo = 0;
  -- Accion 2
  UPDATE alquiler a SET a.fuera_plazo = 1
  WHERE a.fecha_limite < CURDATE()  and a.fecha_entrega is null and a.fuera_plazo = 0;

END//

DELIMITER ;

show events;

/*

SELECT * FROM biblioteca.alquiler;

insert into alquiler(fecha_inicio, fecha_limite, fk_usuario_alquiler, fk_ejemplar)
values ("2022-01-01", "2022-01-15", 1, 1);

insert into alquiler(fecha_inicio, fecha_limite, fk_usuario_alquiler, fk_ejemplar)
values ("2022-01-01", "2025-01-15", 1, 1);

*/