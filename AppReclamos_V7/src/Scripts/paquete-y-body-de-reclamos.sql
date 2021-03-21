CREATE OR REPLACE 
PACKAGE PKG_RECLAMO AS 

  PROCEDURE SP_LISTAR(
        R_CURSOR  OUT SYS_REFCURSOR 
    );
    
    PROCEDURE SP_BUSCAR_X_ESTADO(
        R_CURSOR   OUT SYS_REFCURSOR,
        R_ESTADO   IN RECLAMO.ESTADO%TYPE
    );
    
    PROCEDURE SP_INSERTAR(
        R_ID_RECLAMO            OUT RECLAMO.ID_RECLAMO%TYPE,
        R_DESCRIPCION           IN  RECLAMO.DESCRIPCION%TYPE,
        --R_ESTADO                IN  RECLAMO.ESTADO%TYPE,
        R_ID_USUARIO            IN  RECLAMO.ID_USUARIO%TYPE
    );
    
    PROCEDURE SP_ACTUALIZAR(
        R_ID_RECLAMO            IN RECLAMO.ID_RECLAMO%TYPE,
        R_DESCRIPCION           IN  RECLAMO.DESCRIPCION%TYPE,
        R_ESTADO                IN  RECLAMO.ESTADO%TYPE
        --R_ID_USUARIO            IN  RECLAMO.ID_USUARIO%TYPE
    );
    
    PROCEDURE SP_BUSCAR_X_ID_RECLAMO(
        R_CURSOR        OUT SYS_REFCURSOR,
        R_ID_RECLAMO    IN RECLAMO.ID_RECLAMO%TYPE
    );

END PKG_RECLAMO;


=======================================================================================

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
            RECLAMO.ID_RECLAMO,
            RECLAMO.DESCRIPCION,
            RECLAMO.ESTADO,
            USUARIO.ID_USUARIO,
            USUARIO.DOCUMENTO,
            USUARIO.NOMBRES,
            USUARIO.APELLIDO_PATERNO,
            USUARIO.APELLIDO_MATERNO 
        FROM RECLAMO 
            INNER JOIN USUARIO ON RECLAMO.ID_USUARIO = USUARIO.ID_USUARIO;
  END SP_LISTAR;

  PROCEDURE SP_BUSCAR_X_ESTADO(
        R_CURSOR   OUT SYS_REFCURSOR,
        R_ESTADO   IN RECLAMO.ESTADO%TYPE
    ) AS
  BEGIN
    OPEN
        R_CURSOR
    FOR
        SELECT 
            RECLAMO.ID_RECLAMO,
            RECLAMO.DESCRIPCION,
            RECLAMO.ESTADO,
            USUARIO.ID_USUARIO,
            USUARIO.DOCUMENTO,
            USUARIO.NOMBRES,
            USUARIO.APELLIDO_PATERNO,
            USUARIO.APELLIDO_MATERNO 
        FROM RECLAMO 
            INNER JOIN USUARIO ON RECLAMO.ID_USUARIO = USUARIO.ID_USUARIO
            WHERE RECLAMO.ESTADO=R_ESTADO;
  END SP_BUSCAR_X_ESTADO;

  PROCEDURE SP_INSERTAR(
        R_ID_RECLAMO            OUT RECLAMO.ID_RECLAMO%TYPE,
        R_DESCRIPCION           IN  RECLAMO.DESCRIPCION%TYPE,
        --R_ESTADO                IN  RECLAMO.ESTADO%TYPE,
        R_ID_USUARIO            IN  RECLAMO.ID_USUARIO%TYPE
    ) AS
  BEGIN
    SELECT
            SEQ_RECLAMO.NEXTVAL
        INTO
            R_ID_RECLAMO
        FROM
            DUAL;
  
        INSERT INTO 
            RECLAMO
        (
            ID_RECLAMO,
            DESCRIPCION,
            ESTADO,
            ID_USUARIO
        )
        VALUES
        (
            R_ID_RECLAMO,
            R_DESCRIPCION,
            'En Pendiente',
            R_ID_USUARIO
        );
  END SP_INSERTAR;

  PROCEDURE SP_ACTUALIZAR(
        R_ID_RECLAMO            IN RECLAMO.ID_RECLAMO%TYPE,
        R_DESCRIPCION           IN  RECLAMO.DESCRIPCION%TYPE,
        R_ESTADO                IN  RECLAMO.ESTADO%TYPE
        --R_ID_USUARIO            IN  RECLAMO.ID_USUARIO%TYPE
    ) AS
  BEGIN
    UPDATE
            RECLAMO
        SET
            DESCRIPCION         =   R_DESCRIPCION,
            ESTADO              =   R_ESTADO 
        WHERE
            ID_RECLAMO =   R_ID_RECLAMO;
  END SP_ACTUALIZAR;
  
  PROCEDURE SP_BUSCAR_X_ID_RECLAMO(
        R_CURSOR   OUT SYS_REFCURSOR,
        R_ID_RECLAMO   IN RECLAMO.ID_RECLAMO%TYPE
    ) AS
  BEGIN
    OPEN
        R_CURSOR
    FOR
        SELECT 
            RECLAMO.ID_RECLAMO,
            RECLAMO.DESCRIPCION,
            RECLAMO.ESTADO,
            USUARIO.ID_USUARIO,
            USUARIO.DOCUMENTO,
            USUARIO.NOMBRES,
            USUARIO.APELLIDO_PATERNO,
            USUARIO.APELLIDO_MATERNO 
        FROM RECLAMO 
            INNER JOIN USUARIO ON RECLAMO.ID_USUARIO = USUARIO.ID_USUARIO
            WHERE RECLAMO.ID_RECLAMO=R_ID_RECLAMO;
  END SP_BUSCAR_X_ID_RECLAMO;

END PKG_RECLAMO;