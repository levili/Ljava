package com.l.volley;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.l.constants.ParameteConstant;
import com.l.utils.GsonUtils;

public class GsonRequest<T> extends Request<T> {

	private final Listener<T> mListener;
	private BaseVolleyEvent mdata;
	private Gson mGson;
	private Map<String, String> rMap;
	private Class<?extends BaseVolleyEvent<T>> mClass;

	public GsonRequest(int method, String url, Class<?extends BaseVolleyEvent<T>> clazz,
			T data, Listener<BaseVolleyEvent> listener,
			ErrorListener errorListener) {
		super(method, url, errorListener);
		mClass = clazz;
		mdata = data;
		mListener = listener;
		mGson = new Gson();
		rMap = new HashMap<String, String>();
	}

	@SuppressWarnings("rawtypes")
	public GsonRequest(String url, Class<?extends BaseVolleyEvent<T>> clazz1, T data,
			Listener<BaseVolleyEvent> listener, ErrorListener errorListener) {
		this(Method.POST, url, clazz1, data, listener, errorListener);
	}


	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		try {
			String jsonString = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));
			return (Response<T>) Response.success(mGson.fromJson(jsonString, mClass),
					HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		}
	}

	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		String gsonStr = GsonUtils.getInstance().getGsonStr(mdata);
		rMap.put(ParameteConstant.PARAMETE_KEY, gsonStr);
		return rMap;
	}

	@Override
	protected void deliverResponse(T response) {
		mListener.onResponse(response);
	}
//至親愛的Allen：
//我也就用繁體來打吧 顯得我有逼格一些 你不是經常希望我們能成為有逼格的團隊嘛~~ 從idea-bloc工作到現在108天4個月多一點 一路走來 我真的非常感謝你的照顧和指導!!!
//記得有一回去餐廳吃飯，你點了一杯凍奶茶，結果服務員忘記了，然後你把這事跟領班說了，你講到：“現在對他的嚴格要求，才可以讓他以後變得更好”,你的態度很讓我欣賞,真的
//每回我做的功能出了bug后 你都會很緩和的跟我說levi這樣是不行的，我們應該...然後握拳給我加油. 作為老闆的你能夠這樣 真的需要很好的修養和素質，要是我的話早就氣急敗壞了 哈哈..
//我很喜歡這裡的貓和泡麵，當然還有你們，但我真的很抱歉也很遺憾，我必須告訴你  我可能要離開這裡了。。
//可能是因為我的素質太低，性格太幼稚，想法和做法不夠成熟，每每都會讓我感覺我和Team總是那麼的格格不入，最近的日子我感覺出來Allen你對我十分失望（我沒有不高興，這是我的錯，因為我做了很多不靠譜的事，你的失望是應該的）
//跟我的交流也變得很少了..-_-我挺傷心的 呵呵 傷心自己為什麼沒能做好讓你高興..
//我的家人從小就很庝我 所以也造就了我比較幼稚的想法 覺得人和人之間不要說謊  你對我好 我就對你好 開心就好..現在想來更多的時候人和人之間需要的是承諾 責任 和擔當 把答應別人的事情做好 不讓別人操心 這樣別人才會更加信任你。。
//出來工作說心裡話 我希望的是 開心 成長和薪資.. 開心排在首位 因為我是那種做事情如果不開心的話就會越做越差 把自己也變的更差的人 所以即便再忙一旦有空我也會去找同學說話開心。
//突然想起一件事 記得我和你有過一次咖啡交流 當時你對我說 現在不需要找女朋友 先把自己做好自然會有女生來找你，其實我知道你是希望我能把全部心思放在工作上 我也想過 但我真的做不到 我過不了自己這一關 也許我也無法和聰聰、wesley他們一樣專注和認真
//也許他們才更加適合這個團隊，而我卻沒能找到屬於我的歸屬感，很遺憾~~這也是為什麼沒有能夠開心工作的原因之一。。
//其次我在這裡的成長是巨大不可否認的，但不是指我的代碼能力，而是因為我認識了你們一群有逼格的傢伙，瞬間讓我的逼格也變得金光燦爛起來了 哈哈 ，真的 雖然挺辛苦的（程序員里我最偷懶。。。） 但有辛苦才會有回報嗎。從你們身上我學到了很多懂了很多，再次感謝！
//好吧 我知道我的文筆不是很好 你也快看不下去了吧。。 但沒關係其實要說的並不多 只是有點捨不得所以苦哈哈的碼了一些字。。和allen的緣分我一定會好好保管在記憶里讓它醇香甘甜。我是不是很文藝 哈哈 
//最後告訴你一件小事，我真的不是一張白紙 也沒有任何一個人是一張白紙 也不會是誰想畫什麼上去就能畫什麼上去的 你得看紙願不願意讓你畫。。 我不是好玩的去寫這些話 這都是我的心裡話 是我認真的思考后 決定之後才寫的 我是不回頭的人。
// 																								2014 10 02 Levili
}
