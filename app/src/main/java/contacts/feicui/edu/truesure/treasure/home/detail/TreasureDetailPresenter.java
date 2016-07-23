package contacts.feicui.edu.truesure.treasure.home.detail;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import java.util.List;

import contacts.feicui.edu.truesure.NetClient.NetClient;
import contacts.feicui.edu.truesure.treasure.TreasureApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by liuyue on 2016/7/23.
 */
public class TreasureDetailPresenter extends MvpNullObjectBasePresenter<TreasureDetailView> {

    private Call<List<TreasureDetailResult>> detailCall;
    /**
     * 核心功能：获取宝藏详情
     */
    public void getTreasureDetail(TreasureDetail treasureDetail){
        if (detailCall != null) detailCall.cancel();
        TreasureApi treasureApi = NetClient.getInstance().getTreasureApi();
        detailCall = treasureApi.getTreasureDetail(treasureDetail);
        detailCall.enqueue(callback);

    }

    private final Callback<List<TreasureDetailResult>> callback = new Callback<List<TreasureDetailResult>>() {
        @Override
        public void onResponse(Call<List<TreasureDetailResult>> call, Response<List<TreasureDetailResult>> response) {
            if (response != null && response.isSuccessful()){
                List<TreasureDetailResult> results = response.body();
                if (results == null){
                    getView().showMessage("unknown error");
                    return;
                }
                getView().setData(results);
            }
        }

        @Override
        public void onFailure(Call<List<TreasureDetailResult>> call, Throwable t) {
            getView().showMessage(t.getMessage());
        }
    };
}
