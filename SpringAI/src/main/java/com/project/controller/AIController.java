package com.project.controller;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AIController {

    private final ChatModel chatModel;

    public AIController(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/chat")
    public String chat(@RequestParam String prompt, Model model) {

        try {

            String enhancedPrompt = """
                    You are an educational AI assistant.

                    Format every response using HTML.

                    Rules:
                    - Use <h2> for main headings.
                    - Use <h3> for subheadings.
                    - Use <ul> and <li> for points.
                    - Use <table>, <tr>, <th>, <td> for comparisons.
                    - Use <p> for explanations.
                    - Do NOT return Markdown.
                    - Return only valid HTML.

                    User Question:
                    """ + prompt;

            String response = chatModel
                    		.call(new Prompt(enhancedPrompt))
                    		.getResult()
                    		.getOutput()
                    		.getText();

            model.addAttribute("prompt", prompt);
            model.addAttribute("response", response);

        } catch (Exception e) {
            model.addAttribute("response", "<h3>Error</h3><p>" + e.getMessage() +"</p>");
        }
        return "index";
    }
}



//package com.project.controller;
//
//import org.springframework.ai.chat.model.ChatModel;
//import org.springframework.ai.chat.prompt.Prompt;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class AIController {
//
//    private final ChatModel chatModel;
//
//    public AIController(ChatModel chatModel) {
//        this.chatModel = chatModel;
//    }
//
//    @GetMapping("/")
//    public String home() {
//        return "index";
//    }
//
//    @PostMapping("/chat")
//    public String chat(
//            @RequestParam String prompt,
//            Model model) {
//
//        try {
//
//            String response = chatModel
//                    .call(new Prompt(prompt))
//                    .getResult()
//                    .getOutput()
//                    .getText();
//
//            model.addAttribute("response", response);
//
//        } catch (Exception e) {
//
//            model.addAttribute(
//                    "response",
//                    "Error : " + e.getMessage());
//        }
//
//        return "index";
//    }
//}
