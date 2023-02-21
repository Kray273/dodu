package com.starters.dodu.controller;

import com.starters.dodu.dto.*;
import com.starters.dodu.service.CategoryService;
import com.starters.dodu.service.MentorService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.starters.dodu.utils.StringToUuid.stringtoUUID;

@RequiredArgsConstructor
@RestController
public class ApiController {

  private final MentorService mentorService;

  @GetMapping("/api/v1")
  @ResponseBody
  public List<MentorDTO> index(@RequestParam(defaultValue = "0", required = false) String categoryId) {
    return mentorService.findAllByCategoryId(categoryId);
  }

  @GetMapping("/mypage/{id}")
  public MentorDTO getMentorById(@PathVariable String id) {
    UUID uuid = stringtoUUID(id);
    return mentorService.findById(id);
  }

  @ResponseStatus(HttpStatus.OK)
  @PostMapping("/offer/saveApply")
  public void saveApply(ApplyFormDTO applyFormDTO, HttpServletResponse response) throws IOException {
    mentorService.saveApply(applyFormDTO);
    response.sendRedirect("/");
  }

  @GetMapping("/mypage/{id}/applyList")
  public List<ApplyResultDTO> getApplyListById(@PathVariable String id) {
    UUID uuid = stringtoUUID(id);
    return mentorService.findAllDesc(id);
  }

}
