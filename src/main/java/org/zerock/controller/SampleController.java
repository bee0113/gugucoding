package org.zerock.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {

    @RequestMapping("")
    public void basic() {
        log.debug("basic..............");
    }

    @GetMapping("/ex01")
    public String ex01(SampleDTO dto) {

        log.info("" + dto);

        return "ex01";
    }

    /*동일한 이름의 파라미터가 여러개 전달되는 경우 ArrayList<> 및 배열 사용*/
    /*
     * test URL
     * http://localhost:8080/sample/ex02List?ids=111&ids=222&ids=333&ids2=444&ids2=555&ids2=666
     * console 결과값
     * INFO : org.zerock.controller.SampleController - ids: [111, 222, 333]
     * INFO : org.zerock.controller.SampleController - array ids2: [444, 555, 666]
     * */
    @GetMapping("ex02List")
    public String ex02List(@RequestParam("ids") ArrayList<String> ids,
                           @RequestParam(value = "ids2", required = false) String[] ids2) {

        log.info("ids: " + ids);

        log.info("array ids2: " + Arrays.toString(ids2));

        return "ex02List";
    }

    @GetMapping("/ex02Bean")
    public String ex02Bean(SampleDTOList list) {

        log.info("list dtos: " + list);

        return "ex02Bean";
    }

    // @InitBinder
    // public void initBinder(WebDataBinder binder) {
    // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    // binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,
    // false));
    // }

    @GetMapping("/ex03")
    public String ex03(TodoDTO todo) {
        log.info("todo: " + todo);
        return "ex03";
    }

    /**
     * @param dto
     * @param page
     * @return
     * @ModelAttribute 가 걸린 파라미터는 타입에 관계없이 무조건 Model에 담아서 전달됨
     */
    @GetMapping("/ex04")
    public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {

        log.info("dto: " + dto);
        log.info("page: " + page);

        return "/sample/ex04";
    }

    @GetMapping("/ex05")
    public void ex05() {
        log.info("/ex05..........");
    }

    @GetMapping("/ex06")
    public @ResponseBody
    SampleDTO ex06() {
        log.info("/ex06..........");

        SampleDTO dto = new SampleDTO();
        dto.setAge(10);
        dto.setName("홍길동");

        return dto;
    }

    @GetMapping("/ex07")
    public ResponseEntity<String> ex07() {
        log.info("/ex07..........");

        // {"name": "홍길동"}
        String msg = "{\"name\": \"홍길동\"}";

        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json;charset=UTF-8");

        return new ResponseEntity<>(msg, header, HttpStatus.OK);
    }

    @GetMapping("/exUpload")
    public void exUpload() {
        log.info("/exUpload..........");
    }

    @PostMapping("/exUploadPost")
    public void exUploadPost(ArrayList<MultipartFile> files) {

        files.forEach(file -> {
            log.info("----------------------------------");
            log.info("name:" + file.getOriginalFilename());
            log.info("size:" + file.getSize());

        });
    }
}
