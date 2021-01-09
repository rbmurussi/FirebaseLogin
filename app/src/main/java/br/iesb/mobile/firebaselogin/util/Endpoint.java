package br.iesb.mobile.firebaselogin.util;

import java.util.List;

import br.iesb.mobile.firebaselogin.domain.ItemResult;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Endpoint {
    @GET("posts")
    Call<List<ItemResult>> getPosts();
}
