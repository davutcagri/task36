package davutcagri.task36.controller;

import davutcagri.task36.dto.LessonDTO;
import davutcagri.task36.model.Lesson;
import davutcagri.task36.service.LessonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lesson")
public class LessonController {

    private final LessonService lessonService;


    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping("/save")
    public void save(@RequestBody Lesson lesson) {
       lessonService.save(lesson);
    }

    @GetMapping("/findall")
    public List<LessonDTO> findAll() {
        return lessonService.findAll();
    }

}
