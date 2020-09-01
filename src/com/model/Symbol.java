package com.model;

public enum Symbol {

    X("X") {
        @Override
        public Symbol opposite() {
            return O;
        }
    },
    O("O") {
        @Override
        public Symbol opposite() {
            return X;
        }
    },
    EMPTY(" ") {
        @Override
        public Symbol opposite() {
            return EMPTY;
        }
    };

    private String representation;

    Symbol(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }

    public abstract Symbol opposite();
}
