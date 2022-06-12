package io.lalahtalks.user.gateway.server.domain.user;

public record Password(String value) {

    @Override
    public String toString() {
        return "Password()";
    }

}
