package com.kamijoucen.jsonar.analyse.node

enum class NodeType(val code: Long) {

    CLASS_DEFINITION(1),

    FIELD_DEFINITION(2),

    METHOD_DEFINITION(4),

    METHOD_CALL(3),

    ;

}