CREATE OR REPLACE 
PACKAGE PKG_CLIENTE AS 

   PROCEDURE SP_LISTAR_X_DOCUMENTO(
        C_CURSOR        OUT SYS_REFCURSOR,
        C_DOCUMENTO     IN CLIENTE.DOCUMENTO%TYPE
    );

END PKG_CLIENTE;

===================================================================

  PROCEDURE SP_LISTAR_X_DOCUMENTO(
        C_CURSOR        OUT SYS_REFCURSOR,
        C_DOCUMENTO     IN CLIENTE.DOCUMENTO%TYPE
    ) AS
  BEGIN
    OPEN
        C_CURSOR
    FOR
        SELECT
            ID_CLIENTE,
            TIPO_DOCUMENTO,
            DOCUMENTO,
            NOMBRES,
            APELLIDO_PATERNO,
            APELLIDO_MATERNO,
            DOMICILIO,
            TELEFONO,
            EMAIL
        FROM
            CLIENTE
        WHERE
            UPPER(DOCUMENTO)  LIKE '%'||UPPER(C_DOCUMENTO)||'%';
  END SP_LISTAR_X_DOCUMENTO;