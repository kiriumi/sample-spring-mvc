package sample.spring.mvc.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/room")
public class RoomControler {

	@Autowired
	RoomForm form; // 異なるコントローラー間で情報を引き継ぐ場合

	@RequestMapping(method = RequestMethod.POST)
	public String forwarded(Model model) {
		return "/room/room";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String redirected(Model model) {
		model.addAttribute(form);
		return "/room/room";
	}
}
