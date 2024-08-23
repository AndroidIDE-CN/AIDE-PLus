//
// Decompiled by Jadx - 763ms
//
package com.aide.ui.build.android;

import abcd.cy;
import abcd.dy;
import abcd.ey;
import abcd.fy;
import abcd.gy;
import abcd.hy;
import abcd.iy;
import com.aide.ui.build.android.AaptService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import io.github.zeroaicy.util.Log;
import io.github.zeroaicy.aide.ui.services.ThreadPoolService;

class AaptService$d extends FutureTask<AaptService$ErrorResult> {


    final AaptService aaptService;
    public AaptService$d(AaptService aaptService, AaptService$a aVar) {
        super(aVar);
		this.aaptService = aaptService;
    }
	
	/**
	 * 必须运行在ProjectService所在线程
	 * 否则可能导致并发问题
	 */
    @Override
    protected void done() {
		if (isCancelled()) {
			return;
		}
		try {
			AaptService$ErrorResult aaptService$b = get();
			// 判断aapt是否有错误
			if (aaptService$b.syntaxErrorsMap == null) {
				// 切换到主线程
				AaptService.j6(this.aaptService, aaptService$b.hasError);
			} else {
				AaptService.DW(this.aaptService, aaptService$b.syntaxErrorsMap);
			}
		}
		catch (InterruptedException unused) {
			AaptService.FH(this.aaptService);
		}
		catch (ExecutionException e) {
			AaptService.Hw(this.aaptService, e.getCause());
		}
    }
}

