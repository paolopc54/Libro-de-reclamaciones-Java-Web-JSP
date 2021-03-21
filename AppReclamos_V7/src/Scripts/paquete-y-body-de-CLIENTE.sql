CREATE OR REPLACE 
PACKAGE PKG_USUARIO AS 

   PROCEDURE SP_LISTAR(
        U_CURSOR    OUT SYS_REFCURSOR,
        U_NOMBRES   IN USUARIO.NOMBRES%TYPE
    );
    
    PROCEDURE SP_BUSCAR_X_DOCUMENTO(
        U_CURSOR        OUT SYS_REFCURSOR,
        U_DOCUMENTO     IN USUARIO.DOCUMENTO%TYPE
    );
    
    PROCEDURE SP_INSERTAR(
        U_ID_USUARIO            OUT USUARIO.ID_USUARIO%TYPE,
        U_TIPO_DOCUMENTO        IN  USUARIO.TIPO_DOCUMENTO%TYPE,
        U_DOCUMENTO             IN  USUARIO.DOCUMENTO%TYPE,
        U_NOMBRES               IN  USUARIO.NOMBRES%TYPE,
        U_APELLIDO_PATERNO      IN  USUARIO.APELLIDO_PATERNO%TYPE,
        U_APELLIDO_MATERNO      IN  USUARIO.APELLIDO_MATERNO%TYPE,
        U_DOMICILIO             IN  USUARIO.DOMICILIO%TYPE,
        U_TELEFONO              IN  USUARIO.TELEFONO%TYPE,
        U_EMAIL                 IN  USUARIO.EMAIL%TYPE
    );
    
    PROCEDURE SP_ACTUALIZAR(
        U_ID_USUARIO            IN  USUARIO.ID_USUARIO%TYPE,
        U_TIPO_DOCUMENTO        IN  USUARIO.TIPO_DOCUMENTO%TYPE,
        U_DOCUMENTO             IN  USUARIO.DOCUMENTO%TYPE,
        U_NOMBRES               IN  USUARIO.NOMBRES%TYPE,
        U_APELLIDO_PATERNO      IN  USUARIO.APELLIDO_PATERNO%TYPE,
        U_APELLIDO_MATERNO      IN  USUARIO.APELLIDO_MATERNO%TYPE,
        U_DOMICILIO             IN  USUARIO.DOMICILIO%TYPE,
        U_TELEFONO              IN  USUARIO.TELEFONO%TYPE,
        U_EMAIL                 IN  USUARIO.EMAIL%TYPE
    );
    
    PROCEDURE SP_LISTARDOCUMENTO(
        U_CURSOR    OUT SYS_REFCURSOR,
        U_DOCUMENTO   IN USUARIO.DOCUMENTO%TYPE
    );
    
    PROCEDURE SP_BUSCAR_X_ID_USUARIO(
        U_CURSOR        OUT SYS_REFCURSOR,
        U_ID_USUARIO     IN USUARIO.ID_USUARIO%TYPE
    );

END PKG_USUARIO;

===========================================================================

CREATE OR REPLACE
PACKAGE BODY PKG_USUARIO AS

  PROCEDURE SP_LISTAR(
        U_CURSOR    OUT SYS_REFCURSOR,
        U_NOMBRES IN USUARIO.NOMBRES%TYPE
    ) AS
  BEGIN
    OPEN
        U_CURSOR
    FOR
        SELECT
            ID_USUARIO,
            TIPO_DOCUMENTO,
            DOCUMENTO,
            NOMBRES,
            APELLIDO_PATERNO,
            APELLIDO_MATERNO,
            DOMICILIO,
            TELEFONO,
            EMAIL
        FROM
            USUARIO
        WHERE
            UPPER(NOMBRES)  LIKE '%'||UPPER(U_NOMBRES)||'%';
  END SP_LISTAR;

  PROCEDURE SP_BUSCAR_X_DOCUMENTO(
        U_CURSOR        OUT SYS_REFCURSOR,
        U_DOCUMENTO     IN USUARIO.DOCUMENTO%TYPE
    ) AS
  BEGIN
    OPEN
        U_CURSOR
    FOR
        SELECT
            ID_USUARIO,
            TIPO_DOCUMENTO,
            DOCUMENTO,
            NOMBRES,
            APELLIDO_PATERNO,
            APELLIDO_MATERNO,
            DOMICILIO,
            TELEFONO,
            EMAIL
        FROM
            USUARIO
        WHERE
            DOCUMENTO = U_DOCUMENTO;
  END SP_BUSCAR_X_DOCUMENTO;

  PROCEDURE SP_INSERTAR(
        U_ID_USUARIO            OUT USUARIO.ID_USUARIO%TYPE,
        U_TIPO_DOCUMENTO        IN  USUARIO.TIPO_DOCUMENTO%TYPE,
        U_DOCUMENTO             IN  USUARIO.DOCUMENTO%TYPE,
        U_NOMBRES               IN  USUARIO.NOMBRES%TYPE,
        U_APELLIDO_PATERNO      IN  USUARIO.APELLIDO_PATERNO%TYPE,
        U_APELLIDO_MATERNO      IN  USUARIO.APELLIDO_MATERNO%TYPE,
        U_DOMICILIO             IN  USUARIO.DOMICILIO%TYPE,
        U_TELEFONO              IN  USUARIO.TELEFONO%TYPE,
        U_EMAIL                 IN  USUARIO.EMAIL%TYPE
    ) AS
  BEGIN
    SELECT
            SEQ_USUARIO.NEXTVAL
        INTO
            U_ID_USUARIO
        FROM
            DUAL;
  
        INSERT INTO 
            USUARIO
        (
            ID_USUARIO,
            TIPO_DOCUMENTO,
            DOCUMENTO,
            NOMBRES,
            APELLIDO_PATERNO,
            APELLIDO_MATERNO,
            DOMICILIO,
            TELEFONO,
            EMAIL
        )
        VALUES
        (
            U_ID_USUARIO,
            U_TIPO_DOCUMENTO,
            U_DOCUMENTO,
            U_NOMBRES,
            U_APELLIDO_PATERNO,
            U_APELLIDO_MATERNO,
            U_DOMICILIO,
            U_TELEFONO,
            U_EMAIL
        );
  END SP_INSERTAR;

  PROCEDURE SP_ACTUALIZAR(
        U_ID_USUARIO            IN  USUARIO.ID_USUARIO%TYPE,
        U_TIPO_DOCUMENTO        IN  USUARIO.TIPO_DOCUMENTO%TYPE,
        U_DOCUMENTO             IN  USUARIO.DOCUMENTO%TYPE,
        U_NOMBRES               IN  USUARIO.NOMBRES%TYPE,
        U_APELLIDO_PATERNO      IN  USUARIO.APELLIDO_PATERNO%TYPE,
        U_APELLIDO_MATERNO      IN  USUARIO.APELLIDO_MATERNO%TYPE,
        U_DOMICILIO             IN  USUARIO.DOMICILIO%TYPE,
        U_TELEFONO              IN  USUARIO.TELEFONO%TYPE,
        U_EMAIL                 IN  USUARIO.EMAIL%TYPE
    ) AS
  BEGIN
    UPDATE
            USUARIO
        SET
            TIPO_DOCUMENTO      =   U_TIPO_DOCUMENTO,
            DOCUMENTO           =   U_DOCUMENTO,
            NOMBRES             =   U_NOMBRES,
            APELLIDO_PATERNO    =   U_APELLIDO_PATERNO,
            APELLIDO_MATERNO    =   U_APELLIDO_MATERNO,
            DOMICILIO           =   U_DOMICILIO,
            TELEFONO            =   U_TELEFONO,
            EMAIL               =   U_EMAIL
        WHERE
            ID_USUARIO =   U_ID_USUARIO;
  END SP_ACTUALIZAR;
  
  PROCEDURE SP_LISTARDOCUMENTO(
        U_CURSOR    OUT SYS_REFCURSOR,
        U_DOCUMENTO IN USUARIO.DOCUMENTO%TYPE
    ) AS
  BEGIN
    OPEN
        U_CURSOR
    FOR
        SELECT
            ID_USUARIO,
            TIPO_DOCUMENTO,
            DOCUMENTO,
            NOMBRES,
            APELLIDO_PATERNO,
            APELLIDO_MATERNO,
            DOMICILIO,
            TELEFONO,
            EMAIL
        FROM
            USUARIO
        WHERE
            UPPER(DOCUMENTO)  LIKE '%'||UPPER(U_DOCUMENTO)||'%';
  END SP_LISTARDOCUMENTO;
  
  PROCEDURE SP_BUSCAR_X_ID_USUARIO(
        U_CURSOR        OUT SYS_REFCURSOR,
        U_ID_USUARIO     IN USUARIO.ID_USUARIO%TYPE
    ) AS
  BEGIN
    OPEN
        U_CURSOR
    FOR
        SELECT
            ID_USUARIO,
            TIPO_DOCUMENTO,
            DOCUMENTO,
            NOMBRES,
            APELLIDO_PATERNO,
            APELLIDO_MATERNO,
            DOMICILIO,
            TELEFONO,
            EMAIL
        FROM
            USUARIO
        WHERE
            ID_USUARIO = U_ID_USUARIO;
  END SP_BUSCAR_X_ID_USUARIO;

END PKG_USUARIO;