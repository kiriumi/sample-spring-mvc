package sample.spring.mvc.welcome;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import sample.spring.mvc.db.Sample;
import sample.spring.mvc.room.RoomForm;

@Controller
@RequestMapping("/welcome")
@SessionAttributes(types = WelcomeForm.class) // 同一コントローラー内で情報を引継ぐ場合
public class WelcomeController {

	@Autowired
	public WelcomeService service;

	@Autowired
	public MessageSource messageSource;

	@Value("${sample.config}")
	String sampleConfig;

	@ModelAttribute("welcomeForm") // 同一コントローラー内で情報を引継ぐ場合、使用する（hiddenで引き継げないかも考慮する）
	public WelcomeForm welcomeForm() {
		return new WelcomeForm();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String init(Model model, Locale locale) {

		// メッセージを取得
		String message = messageSource.getMessage("language", new String[] { "あああ" }, locale);
		System.out.println(message);

		// プロパティファイルから情報を取得
		System.out.println(sampleConfig);

		// 画面に情報を設定
		WelcomeForm welcomeForm = new WelcomeForm();
		welcomeForm.setId(0);
		welcomeForm.setName("ななし");
		model.addAttribute(welcomeForm);

		return "/welcome/welcome";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String send(@Valid WelcomeForm welcomeForm, Model model, BindingResult result) {

		if (result.hasErrors()) {
			// エラーの有無チェック
			return "/welcome/welcome";
		}

		int id = welcomeForm.getId();
		Sample dto = service.getDto(id);

		RoomForm roomForm = new RoomForm();
		roomForm.setName(dto.getName());
		roomForm.setRoom(dto.getRoom());
		model.addAttribute(roomForm);

		return "room/room";
	}

	@RequestMapping(method = RequestMethod.POST, params = "forward")
	public String forward(@Valid WelcomeForm welcomeForm, Model model, BindingResult result) {

		if (result.hasErrors()) {
			// エラーの有無チェック
			return "/welcome/welcome";
		}

		int id = welcomeForm.getId();
		Sample dto = service.getDto(id);

		RoomForm roomForm = new RoomForm();
		roomForm.setName(dto.getName());
		roomForm.setRoom(dto.getRoom());
		model.addAttribute(roomForm);

		return "forward:/room"; // フォワード（/roomにPOSTでリクエストする，URLは/welcomeのまま）

	}

	@Autowired
	RoomForm form;

	@RequestMapping(method = RequestMethod.POST, params = "redirect")
	public String redirect(WelcomeForm welcomeForm, Model model) {

		int id = welcomeForm.getId();
		Sample dto = service.getDto(id);

		form.setName(dto.getName());
		form.setRoom(dto.getRoom());
		model.addAttribute(form);

		return "redirect:/room"; // リダイレクト（/roomにGETでリクエストする，URLは/roomになる）

		//		return "redirect:https://www.google.co.jp/"; // 外部へのリダイレクト
	}

}
