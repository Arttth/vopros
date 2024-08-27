package org.arta.vopros.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class JspHelper {
    private static String JSP_FORMAT = "WEB-INF/jsp/%s.jsp";
    public static String getPath(String jspName) {
        return JSP_FORMAT.formatted(jspName);
    }
}
