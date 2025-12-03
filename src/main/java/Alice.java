import java.util.List;

public class Alice {
	public static <T> T third(List<T> list) {
		return list.get(2);
	}
}
