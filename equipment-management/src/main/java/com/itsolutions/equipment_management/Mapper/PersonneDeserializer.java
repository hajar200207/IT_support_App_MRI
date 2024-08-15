//package com.itsolutions.equipment_management.Mapper;
//
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.itsolutions.equipment_management.models.*;
//
//import java.io.IOException;
//
//
//
//import java.io.IOException;
//
//public class PersonneDeserializer extends JsonDeserializer<Personne> {
//    @Override
//    public Personne deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
//        JsonNode node = p.getCodec().readTree(p);
//        String role = null;
//
//        JsonNode roleNode = node.get("role");
//        if (roleNode != null) {
//            role = roleNode.asText();
//        }
//
//        if (role == null) {
//            throw new IllegalArgumentException("Role is missing in the JSON payload");
//        }
//
//        switch (role) {
//            case "ROLE_USER":
//                return p.getCodec().treeToValue(node, User.class);
//            case "ROLE_TECHNICIEN":
//                return p.getCodec().treeToValue(node, Technicien.class);
//            case "ROLE_ADMIN":
//                return p.getCodec().treeToValue(node, Admin.class);
//            default:
//                throw new IllegalArgumentException("Unknown role: " + role);
//        }
//    }
//}
//
//
