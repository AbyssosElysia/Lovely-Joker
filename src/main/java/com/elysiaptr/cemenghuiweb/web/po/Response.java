package com.elysiaptr.cemenghuiweb.web.po;

public class Response {
    private int code;
    private String msg;
    private Object data;

    public Response(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Response okResponse(Object object) {
        return new Response(200, "OK", object);
    }

    public static Response createdResponse() {
        return new Response(201, "Created", null);
    }

    public static Response resetContentResponse() {
        return new Response(205, "Reset Content", null);
    }

    public static Response noContentResponse() {
        return new Response(204, "No Content", null);
    }

    public static Response forbiddenResponse(String data) {
        return new Response(403, "Forbidden", data);
    }

    public static Response notFoundResponse() {
        return new Response(404, "Not Found", null);
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
