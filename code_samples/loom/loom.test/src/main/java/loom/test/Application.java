
package loom.test;

import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Dsl;

public class Application {
    public static void main(String[] args) {
        try (final AsyncHttpClient client = Dsl.asyncHttpClient()) {
            client
                .prepareGet("https://rr1---sn-vgqsrnez.googlevideo.com/videoplayback?expire=1641700999&ei=JwraYd3YGM24mLAP_Iq2uAg&ip=216.131.88.245&id=o-ANOkIh4tA7aJ8qnVkhFop3KLMgmjT3Myg1rmtMMyJBgW&itag=22&source=youtube&requiressl=yes&vprv=1&mime=video/mp4&ns=3-q34mKmFc52Bb6l5TO9fUwG&cnr=14&ratebypass=yes&dur=2898.454&lmt=1634462448609605&fexp=24001373,24007246,24132044&c=WEB&txp=5311224&n=vsyFV2WGkmlJgQ&sparams=expire,ei,ip,id,itag,source,requiressl,vprv,mime,ns,cnr,ratebypass,dur,lmt&sig=AOq0QJ8wRgIhAIPRN9J7YTTy6gvX2B6ZPb_kreRA35h_sMCID6PbXX2BAiEAk-d2qt8neOOGqyx351beE1S8BW2ZMcLoyOQN5Cc1WUU=&title=Project Loom: Modern Scalable Concurrency for the Java Platform — Ron Pressler&rm=sn-5hnelz7e&req_id=b18c09054a61a3ee&ipbypass=yes&cm2rm=sn-uxa0n-t8gs7r,sn-nx5sy7e&redirect_counter=3&cms_redirect=yes&mh=ji&mip=2001:569:77f5:d000:bdbf:1570:ce26:1e92&mm=34&mn=sn-vgqsrnez&ms=ltu&mt=1641679241&mv=m&mvi=1&pl=47&lsparams=ipbypass,mh,mip,mm,mn,ms,mv,mvi,pl&lsig=AG3C_xAwRQIgbak76YOV9Ev-7Zd94cutSy4gLh8d-vMRsUUKPg9hOOQCIQC0df3acgMdKETlx2-f3D4hrLzITxPVceLcLG8DhnPfXg==")
                .addHeader("Alt-Used", "rr1---sn-nx5s7n7z.googlevideo.com")
                .execute()
                .toCompletableFuture()
                .thenAccept(response -> {
                    final byte[] responseBody = response.getResponseBodyAsBytes();
                    try {
                        Files.write(Path.of("test.mp4"), responseBody);
                    } catch (IOException e) {
                        throw new IllegalStateException(e);
                    }
                })
                .thenAccept(unused -> System.out.println("Done"))
                .join();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
