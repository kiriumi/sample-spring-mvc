package sample.spring.mvc.room;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * 部屋
 *
 * 異なるコントローラ間で引継ぐ場合に行うこと
 * ・対象のクラスにアノテーションを付与
 * ・コントローラでAutwiredにより、フィールドで受け取る
 *
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS) // 異なるコントローラー間で情報を引き継ぐ場合
public class RoomForm implements Serializable {

	private String name;
	private String room;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}
}
