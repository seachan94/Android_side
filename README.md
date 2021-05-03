# Android_side
side _project 
- TODO & ALARM
- DB - using room

[2021- 01 - 25] Commit Db & RecyclerView   
[2021 - 02 - 05] check git status & change viewalarm.xml   
[2021 - 02 - 08] Apply Enroll DB System   
[2021 - 02 - 09] Apply Delete DB System ( ALL DELETE ) Doing Redraw Alarm View   
[2021 - 02 - 10] refreash AlarmView   
[2021 - 02 - 25] refreash all code   
[2021 - 03 - 08] delete alarm fun - fin   
[2021 - 03 - 09] Add todo Register   
[2021 - 03 - 10] Add todo View (register)   
[2021 - 04 - 08] Add todo deadline data   
## [2021 - 04 - 10] 
    
    ## TODO :
    1. Todo List 적용시 마감기한 미 입력시 사용자에게 재질문

      - 문구 : 마감일을 미등록 하시나요?
      1) “오냐” 버튼 클릭시 마감 기한 미등록 된 상태로 DB 저장
      2) “큰일날뻔” 버튼 클릭시 마감 등록 pop-up 창으로 이동

    2. 저장 된 DB View에 노출 


    3. TIme Picker  POP-UP 형태로 수정 


    4.TODO 일정 전체 삭제 해결


    5.각각의 Todo 일정 삭제 해결

   
## [2021 - 04 - 12 ]   

    ##TODO :
    
    1. Alarm Manager 복습
    2. alarmReceivcer 추가
    3. calendar setting


## [2021 - 04 0 13]

    ##TODO :
    
    1. Alarm Manager to Receiver 확인 ( 에뮬레이터 시간 설정... )
   
    정리 내용 
    
    setExactAndAllowWhileIdle(int type, long triggerAtMillis, PendingIntent operation

    
    type : 알람의 타입으로 4가지가 있습니다.
    •   RTC_WAKEUP : 2번째 인자로 넘겨준 시간에 맞게 알림이 울리며 잠들어 있는 기기도 깨웁니다.
    •   RTC : RTC_WAKEUP 과 유사하지만 해당 타입은 잠들어 있는 기기는 깨우지 않습니다.
    •   ELAPSED_REALTIME_WAKEUP : 기기가 부팅된 시점으로부터 2번째 인자로 받은 시간이 경과된 후에 알람이 울리며 잠들어 있는 기기도 깨웁니다.
    •   ELAPSED_REALTIME : ELAPSED_REALTIME_WAKEUP과 유사하지만 잠들어 있는 기기는 깨우지 않습니다.

    triggerAtMillis : 타입에 따른 시간입니다.  
    •   RTC, RTC_WAKEUP : 시간을 밀리세컨드 단위로 바꾼 값을 넣어줍니다. 저는 그래서 주로 캘린더를 사용해 가져옵니다. 예시는 아래를 참고해주세요. 캘린더가 아닌          
        'System.currentTimeMillis()' 를 사용하면 현재 시간을 밀리세컨드로 반환 받아, 원하는 시간을 더한 값을 입력하면 더한 시간 뒤에 알림이 울립니다.
    •   ELAPSED_REALTIME, ELAPSED_REALTIME_WAKEUP : 부팅된 이후의 시간 값으로 마찬가지로 밀리세컨드 단위기 때문에 1000이 1초입니다. 'SystemClock.elapsedRealTime()'를 사용해서 부팅            이후 경과된 시간을 반환 받고 해당 값에 원하는 시간을 더한 값을 입력하면 더한 시간 뒤에 알림이 울립니다.

    pendingIntent : 알림이 울렸을때 수행한 작업을 명시하며 receiver를 사용합니다

## [2021 - 04 - 26]

    ##TODO
    
    1. Alarm 삭제 시 Alarm Manager에 등록 된 Alarm 취소 -> 전체 삭제/ 각각 삭제 모두 완료
    2. Alarm 등록시 반복성이라면 (주기성? ) setRepeating을 이용해 interval 걸어줌 ( 하루 기준)

    
    - getid in AlarmHandler -> 전체 삭제시 alarm DB에 id 값을 구하기 위해 만들어진 함수
    - cancelAlarm in AlarmHandler -> 전체 삭제 & 각각 삭제시 id 값에 따라 알람 취소 해줌

    next todo 
    1. 알람 Noti
    2.  알람 등록시 AlarmManager RequestId 조정
   
## [2021 - 04 - 27]

    ##TODO
    
    1. DB 저장시 바로 id 값 가져와 알람 매니저에 등록
    2. 알람 등록시 id값 처리하는 방법 동기화 처리
        -> 비동기 동작임으로 ALARMID 가 설정이 되지 않고 처리 된다.
    3. ALARM RECEIVER에서 알람 동작 받는 것 확인 / Toast and Log

    NEXT TO DO
    
    1. 알람 노티 UI 그리기
    
## [2021 - 05 - 03]

    ##TODO
    
    1. 알람 noti UI 그리기
        
        해당 사항은 img 넣고 font 넣는 것으로 대체 ...(오랜만에 해보고 싶었음)
        
    WHAT U KNOW?
    
        1. FLAG_ACTIVITY_NEW_TASK -> Activity가 아닌 클래스에서 startActivity를 통해 activitiy 실행시 이를 처리해주는 코드 필요
        
