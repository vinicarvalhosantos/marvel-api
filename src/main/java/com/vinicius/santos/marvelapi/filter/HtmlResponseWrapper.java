package com.vinicius.santos.marvelapi.filter;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class HtmlResponseWrapper extends HttpServletResponseWrapper {

    private final ByteArrayOutputStream byteArrayOutputStream;
    private ServletOutputStream servletOutputStream;
    private PrintWriter printWriter;

    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @param response The response to be wrapped
     * @throws IllegalArgumentException if the response is null
     */
    public HtmlResponseWrapper(HttpServletResponse response) {
        super(response);
        this.byteArrayOutputStream = new ByteArrayOutputStream(response.getBufferSize());
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (printWriter != null) {
            throw new IllegalArgumentException("Writter already called");
        }

        if (servletOutputStream == null) {
            servletOutputStream = new ServletOutputStream() {

                @Override
                public void write(int b) {
                    byteArrayOutputStream.write(b);
                }

                @Override
                public void flush() throws IOException {
                    byteArrayOutputStream.flush();
                }

                @Override
                public boolean isReady() {
                    return false;
                }

                @Override
                public void setWriteListener(WriteListener listener) {
                }

                @Override
                public void close() throws IOException {
                    byteArrayOutputStream.close();
                }
            };
        }
        return servletOutputStream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (servletOutputStream != null) {
            throw new IllegalArgumentException("getOutputStream() already called");
        }
        if (printWriter == null) {
            printWriter = new PrintWriter(new OutputStreamWriter(byteArrayOutputStream, getCharacterEncoding()));
        }

        return printWriter;
    }

    @Override
    public void flushBuffer() throws IOException {
        super.flushBuffer();
        if (printWriter != null) {
            printWriter.flush();
        } else if (servletOutputStream != null) {
            servletOutputStream.flush();
        }
    }

    public byte[] getCaptureAsBytes() throws IOException {
        if (printWriter != null) {
            printWriter.close();
        } else if (servletOutputStream != null) {
            servletOutputStream.close();
        }
        return byteArrayOutputStream.toByteArray();
    }

    public String getCaptureAsString() throws IOException {
        return new String(getCaptureAsBytes(), getCharacterEncoding());
    }

    public Object getCaptureAsObject() throws IOException {
        return new ObjectMapper().readValue(getCaptureAsBytes(), Object.class);
    }

}
