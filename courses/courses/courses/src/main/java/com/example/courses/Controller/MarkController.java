package com.example.courses.Controller;

import com.example.courses.RequestObjects.MarkCreateRequestDTO;
import com.example.courses.RequestObjects.MarkRequestDTO;
import com.example.courses.ResponseObjects.MarkResponseDTO;
import com.example.courses.Entity.Mark;
import com.example.courses.Service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "*")
public class MarkController {
    @Autowired
    MarkService markService;

    @PostMapping("/createMark")
    public ResponseEntity< MarkResponseDTO > createMark(@RequestBody MarkCreateRequestDTO requestObj) throws Exception {
       MarkCreateRequestDTO.validCreateMarkRequest(requestObj);
        MarkResponseDTO mark = markService.saveMark(requestObj);
        return ResponseEntity.status(HttpStatus.CREATED).body(mark);

    }

    @GetMapping("/getAllMark")
    public List<MarkResponseDTO> getAllMark() {
        return  markService.getAllMark();
    }
    @GetMapping("/getMarkById")
    public Mark getMark(@RequestParam int id) throws Exception {

        return markService.getMarkById(id);
    }

    @PutMapping("/UpdateMark")
    public MarkResponseDTO updateMark(@RequestBody MarkRequestDTO updateObjFromUser) throws Exception {

        return markService.updateMark(updateObjFromUser);
    }

    @DeleteMapping("/deleteMark")
    public String deleteMark(@RequestParam int id) throws Exception {
        markService.deleteMark(id);
        return "mark deleted successfully";
    }


}







