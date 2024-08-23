package org.arta.vopros.dto;

public record UserFilter(String user_nickname, String user_name, String user_lastname, int limit, int offset) {
}
