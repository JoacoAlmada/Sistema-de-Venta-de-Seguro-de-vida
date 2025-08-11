package com.bitsio.ficticiaprueba.dtos.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ErrorApiTest {
    @Test
    public void testConstructorSinParametros() {
        ErrorApi errorApi = new ErrorApi();

        assertNotNull(errorApi);
        assertNull(errorApi.getTimestamp());
        assertNull(errorApi.getStatus());
        assertNull(errorApi.getError());
        assertNull(errorApi.getMessage());
    }

    @Test
    public void testSettersYGetters() {
        ErrorApi errorApi = new ErrorApi();
        String timestampEsperado = "2024-01-15T10:30:00Z";
        Integer statusEsperado = 404;
        String errorEsperado = "Not Found";
        String messageEsperado = "El recurso solicitado no fue encontrado";

        errorApi.setTimestamp(timestampEsperado);
        errorApi.setStatus(statusEsperado);
        errorApi.setError(errorEsperado);
        errorApi.setMessage(messageEsperado);

        assertEquals(timestampEsperado, errorApi.getTimestamp());
        assertEquals(statusEsperado, errorApi.getStatus());
        assertEquals(errorEsperado, errorApi.getError());
        assertEquals(messageEsperado, errorApi.getMessage());
    }

    @Test
    public void testSetTimestamp() {
        ErrorApi errorApi = new ErrorApi();
        String timestampEsperado = "2024-06-30T14:25:30Z";

        errorApi.setTimestamp(timestampEsperado);

        assertEquals(timestampEsperado, errorApi.getTimestamp());
    }

    @Test
    public void testSetStatus() {
        ErrorApi errorApi = new ErrorApi();
        Integer statusEsperado = 500;

        errorApi.setStatus(statusEsperado);

        assertEquals(statusEsperado, errorApi.getStatus());
    }

    @Test
    public void testSetError() {
        ErrorApi errorApi = new ErrorApi();
        String errorEsperado = "Internal Server Error";

        errorApi.setError(errorEsperado);

        assertEquals(errorEsperado, errorApi.getError());
    }

    @Test
    public void testSetMessage() {

        ErrorApi errorApi = new ErrorApi();
        String messageEsperado = "Ocurrió un error interno del servidor";

        errorApi.setMessage(messageEsperado);

        assertEquals(messageEsperado, errorApi.getMessage());
    }

    @Test
    public void testSetTimestampConNull() {
        ErrorApi errorApi = new ErrorApi();

        errorApi.setTimestamp(null);

        assertNull(errorApi.getTimestamp());
    }

    @Test
    public void testSetStatusConNull() {
        ErrorApi errorApi = new ErrorApi();

        errorApi.setStatus(null);

        assertNull(errorApi.getStatus());
    }

    @Test
    public void testSetErrorConNull() {
        ErrorApi errorApi = new ErrorApi();

        errorApi.setError(null);

        assertNull(errorApi.getError());
    }

    @Test
    public void testSetMessageConNull() {
        ErrorApi errorApi = new ErrorApi();

        errorApi.setMessage(null);

        assertNull(errorApi.getMessage());
    }

    @Test
    public void testCodigosDeErrorComunes() {
        ErrorApi errorApi = new ErrorApi();

        errorApi.setStatus(400);
        errorApi.setError("Bad Request");
        errorApi.setMessage("La solicitud es inválida");

        assertEquals(400, errorApi.getStatus());
        assertEquals("Bad Request", errorApi.getError());
        assertEquals("La solicitud es inválida", errorApi.getMessage());

        errorApi.setStatus(401);
        errorApi.setError("Unauthorized");
        errorApi.setMessage("No autorizado");

        assertEquals(401, errorApi.getStatus());
        assertEquals("Unauthorized", errorApi.getError());
        assertEquals("No autorizado", errorApi.getMessage());

        errorApi.setStatus(403);
        errorApi.setError("Forbidden");
        errorApi.setMessage("Acceso prohibido");

        assertEquals(403, errorApi.getStatus());
        assertEquals("Forbidden", errorApi.getError());
        assertEquals("Acceso prohibido", errorApi.getMessage());
    }

    @Test
    public void testValoresLimiteStatus() {
        ErrorApi errorApi = new ErrorApi();

        Integer statusMinimo = 100;
        errorApi.setStatus(statusMinimo);
        assertEquals(statusMinimo, errorApi.getStatus());

        Integer statusMaximo = 599;
        errorApi.setStatus(statusMaximo);
        assertEquals(statusMaximo, errorApi.getStatus());

        Integer statusExtremo = Integer.MAX_VALUE;
        errorApi.setStatus(statusExtremo);
        assertEquals(statusExtremo, errorApi.getStatus());
    }

    @Test
    public void testCamposVacios() {
        ErrorApi errorApi = new ErrorApi();

        errorApi.setTimestamp("");
        errorApi.setError("");
        errorApi.setMessage("");

        assertEquals("", errorApi.getTimestamp());
        assertEquals("", errorApi.getError());
        assertEquals("", errorApi.getMessage());
    }

    @Test
    public void testModificacionesMultiples() {
        ErrorApi errorApi = new ErrorApi();

        errorApi.setStatus(404);
        errorApi.setError("Not Found");
        assertEquals(404, errorApi.getStatus());
        assertEquals("Not Found", errorApi.getError());

        errorApi.setStatus(500);
        errorApi.setError("Internal Server Error");
        assertEquals(500, errorApi.getStatus());
        assertEquals("Internal Server Error", errorApi.getError());

        errorApi.setStatus(200);
        errorApi.setError("OK");
        assertEquals(200, errorApi.getStatus());
        assertEquals("OK", errorApi.getError());
    }

    @Test
    public void testEscenarioCompleto() {
        ErrorApi errorApi = new ErrorApi();
        String timestamp = "2024-06-30T12:00:00Z";
        Integer status = 422;
        String error = "Unprocessable Entity";
        String message = "Los datos enviados no son válidos para el procesamiento";

        errorApi.setTimestamp(timestamp);
        errorApi.setStatus(status);
        errorApi.setError(error);
        errorApi.setMessage(message);

        assertEquals(timestamp, errorApi.getTimestamp());
        assertEquals(status, errorApi.getStatus());
        assertEquals(error, errorApi.getError());
        assertEquals(message, errorApi.getMessage());

        assertNotNull(errorApi);

        assertNotNull(errorApi.getTimestamp());
        assertNotNull(errorApi.getStatus());
        assertNotNull(errorApi.getError());
        assertNotNull(errorApi.getMessage());
    }

    @Test
    void testAllArgsConstructor() {
        ErrorApi error = new ErrorApi("2025-07-01T20:00:00", 404, "NOT_FOUND", "Recurso no encontrado");

        assertEquals("2025-07-01T20:00:00", error.getTimestamp());
        assertEquals(404, error.getStatus());
        assertEquals("NOT_FOUND", error.getError());
        assertEquals("Recurso no encontrado", error.getMessage());
    }

    @Test
    void testBuilder() {
        ErrorApi error = ErrorApi.builder()
                .timestamp("2025-07-01T20:00:00")
                .status(500)
                .error("INTERNAL_SERVER_ERROR")
                .message("Ocurrió un error inesperado")
                .build();

        assertEquals("2025-07-01T20:00:00", error.getTimestamp());
        assertEquals(500, error.getStatus());
        assertEquals("INTERNAL_SERVER_ERROR", error.getError());
        assertEquals("Ocurrió un error inesperado", error.getMessage());
    }
}
