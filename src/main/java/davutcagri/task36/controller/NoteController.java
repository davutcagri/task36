package davutcagri.task36.controller;

import davutcagri.task36.dto.NoteDTO;
import davutcagri.task36.model.Note;
import davutcagri.task36.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/save")
    public NoteDTO save(@RequestBody Note note) {
        return noteService.save(note);
    }

    @GetMapping("/findall")
    public List<NoteDTO> findAll() {
        return noteService.findAll();
    }

}
