Feature: como usuario del banco
  quiero registrarme en el sistema
  para acceder a una cuenta

  Background:
    Given que el usuario se encuentra en la pagina web de registro

  Scenario: Registro exitoso
    When el usuario ingresa todos los campos del formulario y confirma la accion
    Then el sistema debera mostrar por pantalla un mensaje de bienvenida.

  Scenario: Registro fallido por usuario existente
    When el usuario ingresa un username existente
    Then el sistema debera mostrar un mensaje de usuario existente