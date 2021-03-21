CREATE OR REPLACE 
PACKAGE PKG_RECLAMO AS 

    PROCEDURE SP_LISTAR(
        R_CURSOR  OUT SYS_REFCURSOR 
    );
    
   PROCEDURE SP_LISTAR_X_ESTADO(
        R_CURSOR   OUT SYS_REFCURSOR,
        R_ESTADO   IN RECLAMO.ESTADO%TYPE
    );

END PKG_RECLAMO;

===================================================

CREATE OR REPLACE
PACKAGE BODY PKG_RECLAMO AS

  PROCEDURE SP_LISTAR(
        R_CURSOR  OUT SYS_REFCURSOR 
    ) AS
  BEGIN
    OPEN
        R_CURSOR
    FOR
        SELECT 
            ID_RECLAMO,
            DESCRIPCION,
            ESTADO,
            ID_CLIENTE,
            TIPO_DOCUMENTO,
            DOCUMENTO,
            NOMBRES,
            APELLIDO_PATERNO,
            APELLIDO_MATERNO,
            DOMICILIO,
            TELEFONO,
            EMAIL
        FROM V_RECLAMO_CLIENTE;
  END SP_LISTAR;
  
  
  PROCEDURE SP_LISTAR_X_ESTADO(
        R_CURSOR   OUT SYS_REFCURSOR,
        R_ESTADO   IN RECLAMO.ESTADO%TYPE
    ) AS
  BEGIN
    OPEN
        R_CURSOR
    FOR
        SELECT 
            ID_RECLAMO,
            DESCRIPCION,
            ESTADO,
            ID_CLIENTE,
            TIPO_DOCUMENTO,
            DOCUMENTO,
            NOMBRES,
            APELLIDO_PATERNO,
            APELLIDO_MATERNO,
            DOMICILIO,
            TELEFONO,
            EMAIL
        FROM V_RECLAMO_CLIENTE
        WHERE
            UPPER(ESTADO)  LIKE '%'||UPPER(R_ESTADO)||'%';
  END SP_LISTAR_X_ESTADO;

END PKG_RECLAMO;