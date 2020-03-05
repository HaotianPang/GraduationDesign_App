package shou.traceability.View;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.yzq.zxinglibrary.encode.CodeCreator;

import shou.traceability.R;
import shou.traceability.presenter.BasePresenter;

public class QRCodeActivity extends BaseActivity {

    private ImageView iv_qrcode;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        initComponent();
        bitmap = CodeCreator.createQRCode("435yuefyhjdfgh", 400, 400, null);
        iv_qrcode.setImageBitmap(bitmap);
    }

    @Override
    protected void initComponent() {
        super.initComponent();
        iv_qrcode=(ImageView) findViewById(R.id.iv_qrcode);
    }
}
