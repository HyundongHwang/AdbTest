# 목적
- 안드로이드앱에서 adb를 제어하기 위해서 정확히는 adbd를 제어하기 위해서 만든 POC이다.
- `setprop` 명령어를 실행하면 adbd가 재시작 될수 있다는 희망을 갖고 진행하고 있다.

# 근거자료

```text
offline 인경우에 device에서 daemon이 정상적으로 동작 하지 않으므로,
shell상에서 property의 sys.usb.config adb setting 을 다시 해주면 
adbd stop /adbd start가 포함 되어 있으므로 
"/system/bin/setprop sys.usb.config adb" 이 명령을 실행하면 
adb restart 효과를 얻게 된다.
```

# 스크린샷
![](https://hhdpublish.blob.core.windows.net/pub/Ictiarinfriu/KakaoTalk_20180119_161603330.png)