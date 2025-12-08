package com.example.courses.Controller;
///
import com.example.courses.DTO.MarkCreateRequestDTO;
import com.example.courses.DTO.MarkResponseDTO;
import com.example.courses.Entity.Mark;
import com.example.courses.Service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "*")
public class MarkController {
    @Autowired
    MarkService markService;

    @PostMapping("/createMark")
    public MarkResponseDTO createMark(@RequestBody MarkCreateRequestDTO requestObj) {
        Mark mark = markService.saveMark(requestObj);
        return mark;

    }

    @GetMapping("/getAllMark")
    public List<MarkResponseDTO> getAllMark() {
        List<MarkResponseDTO> responseList = markService.getAllMark();
        System.out.println(responseList);
        return responseList;
    }

    @GetMapping("/getMarkById")
    public MarkResponseDTO getMark(@RequestParam int id) throws Exception {

        return markService.getMarkById(id);
    }

    @PutMapping("/UpdateMark")
    public MarkResponseDTO updateMark(@RequestBody MarkCreateRequestDTO updateObjFromUser) throws Exception {

        return markService.updateMark(updateObjFromUser);
    }

    @DeleteMapping("/deleteMark")
    public String deleteMark(@RequestParam int id) throws Exception {
        markService.deleteMark(id);
        return "mark deleted successfully";
    }


}




///

}
