package scaffold.framework.demo.controller;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import scaffold.framework.demo.entity.Utilisateur;

@Controller
public class UtilisateurController {

    @GetMapping("/exportcsv")
    // tsy maintsy mamorona objet vaovao mitokanan fa tsy mahazo ilay objet antsika
    // ireny
    public ResponseEntity<StreamingResponseBody> getMethodName() {

        StreamingResponseBody stream = outputStream -> {
            Utilisateur[] users;
            try {
                users = new Utilisateur().select();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            List<Utilisateur> ls = new ArrayList<Utilisateur>();
            for (int i = 0; i < users.length; i++) {
                ls.add(users[i]);
            }
            try (Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
                try {
                    new StatefulBeanToCsvBuilder<Utilisateur>(writer)
                            .build().write(ls);
                } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        };

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("text/csv; charset=UTF-8"))
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s", "book_details.csv"))
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION)
                .body(stream);
    }

}
