package loom.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

public final class LoomTest {

    public static void main(String[] args) throws Exception {
        final LoomTest loom = new LoomTest();
        Thread.sleep(3000L);
        final byte[] response = loom.retrieveURL(new URL("https://rr3---sn-4g5lznez.googlevideo.com/videoplayback?expire=1644218294&ei=VnMAYqGUAd-9x_APkpSoIA&ip=45.192.144.143&id=o-AAyWgTGbme7HItcND5HuCPpGHHGElDqMBTfk9AyM0F8i&itag=22&source=youtube&requiressl=yes&mh=xu&mm=31%2C26&mn=sn-4g5lznez%2Csn-5hne6nsr&ms=au%2Conr&mv=m&mvi=3&pl=24&initcwndbps=405000&vprv=1&mime=video%2Fmp4&cnr=14&ratebypass=yes&dur=818.758&lmt=1630402012951643&mt=1644196607&fvip=3&fexp=24001373%2C24007246&c=ANDROID&txp=5532434&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cvprv%2Cmime%2Ccnr%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRAIgNbXAHmclj_vaQoSpnMJuTMIJOSpJmYMIkQG_Xa7tLtUCIBX1c7aovmpGJPdXv5kRAdr9qjHImVaTiiJHYsjfNkbw&lsparams=mh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AG3C_xAwRAIgKm1KyAuNm8hZZdF48ZzyFivqidVorczWslbYB9CoNYsCIFupskv0R1oSub_koVj6jTXN2JSjWCuiHsKvKgpec2ES&title=Y2Mate.is%20-%20FLAKEY%20HOMEMADE%20CROISSANTS%20(Beginner%20Friendly)-mT4cqHc4HqU-720p-1644196694599"));

    }

    private record URLData(URL url, byte[] response) { }

    private URLData getURL(URL url) throws IOException {
        try (final InputStream in = url.openStream()) {
            return new URLData(url, in.readAllBytes());
        }
    }

    byte[] retrieveURL(URL url) throws Exception {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            final Callable<URLData> callable = () -> getURL(url);
            return executor.submit(callable).get().response;
        }
    }
}

