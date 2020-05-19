package de.mle.stackoverflow.mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.Test;

public class CloseableHttpClientTest {
    private final CloseableHttpClient client = mock(CloseableHttpClient.class);

    @Test
    public void mockClient() throws IOException {
        // given
        CloseableHttpResponse resp1 = mock(CloseableHttpResponse.class);
        CloseableHttpResponse resp2 = mock(CloseableHttpResponse.class);
        HttpPost post1 = new HttpPost("http://127.0.0.1:8000/new/a");
        HttpPost post2 = new HttpPost("http://127.0.0.1:8000/new/a/b");
        when(client.execute(post1)).thenReturn(resp1);
        when(client.execute(post2)).thenReturn(resp2);

        // when
        CloseableHttpResponse returnedResp1 = client.execute(post1);
        CloseableHttpResponse returnedResp2 = client.execute(post2);

        // then
        assertThat(returnedResp1).isSameAs(resp1);
        assertThat(returnedResp2).isSameAs(resp2);
        verify(client).execute(post1);
        verify(client).execute(post2);
    }
}
