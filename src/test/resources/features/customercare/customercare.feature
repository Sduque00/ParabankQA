Feature: como usuario del banco
  quiero hacer uso del customer care
  para realizar una PQRS

  Background:
    Given que el usuario se encuentra en la pagina web

  Scenario: Solicitud exitosa
    When el usuario ingresa los campos del formulario y confirma la accion
    Then el sistema debera mostrar un mensaje de sulicitud enviada

  Scenario: solicitud fallida por email incompleto
    When el usuario no ingresa el email
    Then el sistema debera mostrar por pantalla un mensaje de email requerido



