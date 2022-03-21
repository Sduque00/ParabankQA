Feature: como usuario del banco
    quiero loguearme en el sistema
    para acceder a una cuenta

    Background:
        Given que el usuario se encuentra en la pagina web

    Scenario: Logueo exitoso
        When el usuario ingresa usuario y clave y confirma la accion
        Then el sistema debera mostrar un mensaje de bienvenida.

    Scenario: Logueo fallido por clave incorrecta
        When el usuario ingresa una clave incorrecta
        Then el sistema debera mostrar un mensaje de clave incorrecta