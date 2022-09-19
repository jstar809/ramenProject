package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.ProductDAO;
import model.ProductVO;
import view.ProductView;

public class ProductController {
   private ProductDAO model;
   private ProductView view;

   public ProductController() {
      model = new ProductDAO();
      view = new ProductView();
   }

   public void startApp() {
      ProductVO resultVO = new ProductVO(); // 장바구니 (선택한 메뉴 담기)

      while (true) {
         view.startView(); // 1) 메뉴 선택 2) 프로그램 종료 3) 관리자모드
         if (view.action == 1) { // 1) 메뉴 선택

            // 라면선택(필수) --> 물의 양 선택(필수) --> 매운 맛 정도 선택(필수) --> 토핑 선택(미선택 가능) --> 사이드메뉴
            // 선택(미선택 가능) --> 주문완료
            ArrayList<ProductVO> allMenu = null;

            ProductVO vo = new ProductVO();
            
            ProductVO ramenVO = new ProductVO();	// 주문한 라면
            ProductVO topingVO = new ProductVO();	// 주문한 토핑
            ProductVO sideVO = new ProductVO();		// 주문한 사이드 메뉴
            
            allMenu = model.ramenselectAll(vo); // 전체 라면 메뉴
            
            // [C] : 유효성 검사 1,2 -- 메뉴 목록 비어있거나, 모든 메뉴 재고X
            int total = 0; // 전체 재고의 합
            for(int i = 0; i < allMenu.size(); i++) {
               total += allMenu.get(i).getCnt();
            }
            boolean flag1 = allMenu.size() == 0; // 라면 메뉴 목록 비어있을 때
            boolean flag2 = total < 1; // 메뉴가 존재하더라도 모든 메뉴의 재고가 없을 경우
            
            if (flag1 || flag2) {
               view.readyMenu(); // 재고부족 메시지
               continue;
            } // 라면 선택은 필수 --> 선택할 수 없다면 더 이상 진행 불가

            // [C] : 유효성 검사3
            while (!(flag1 || flag2)) { // 재고 있는 메뉴 선택할 때까지 반복
               view.ramen();
               view.user_RamenMenu(allMenu); // 라면 메뉴 목록 출력
               // 라면 고르고
               // 사용자 입력 --> PK로 전환 후 저장
               if (view.action == allMenu.size() + 1) {
                  // 처음으로 돌아가기
                  break;
               }

               vo.setNum(allMenu.get(view.action - 1).getNum());
               vo.setCnt(-1); // 재고 -1 감소
               
               ramenVO = model.ramenselectOne(vo);	// 주문한 라면 저장
               if (ramenVO.getCnt() > 0) { // 선택한 메뉴 재고 있으면 다음으로
                  // 선택 라면 저장
                  resultVO.setRamen(ramenVO.getRamen());
                  resultVO.setPrice(ramenVO.getPrice());
                  break; // 재고 있으면 다음으로
               }
               view.soldOut(); // 선택한 메뉴가 재고 없을 때
               vo.setNum(0); // 번호, 재고 reset
            }

            if (!model.ramenUpdate(vo)) { // 선택 라면을 찾을 수 없는 경우
               if (view.action == allMenu.size() + 1) {
                  view.back(); // 처음으로 돌아가기
               } else {
                  view.fail(); // 굉장히 큰 영향 --> 사용자 결제 금액에 영향을 줄 수 있기 때문에
               } // 처음으로 돌아가기
               continue;
            }
            // 라면 선택 성공 --> 매운맛 선택
            view.shoppingBasket();

            do { // [C] 유효성검사 : 선택한 매운 맛 정도가 실제 메뉴에 존재하는지
               vo = new ProductVO();
               allMenu = model.spicyselectAll(vo);
               view.spicy();
               view.user_SpicyMenu(allMenu); // 매운맛 목록
               if (view.action == allMenu.size() + 1) {
                  break; // 처음으로 돌아가기
               }
               vo.setNum(allMenu.get(view.action - 1).getNum());
               // 선택 매운 맛 저장
               resultVO.setSpicy(model.spicyselectOne(vo).getSpicy());
               view.shoppingBasket();
            } while (model.spicyselectOne(vo) == null);
            if (view.action == allMenu.size() + 1) {
            	ramenVO.setCnt(ramenVO.getCnt() + 1);	// 주문한 라면 메뉴 재고 원래대로
               view.back(); // 처음으로 돌아가기
               continue;
            }
            do { // [C] 유효성검사 : 선택한 물의 양 정도가 실제 메뉴에 존재하는지
               vo = new ProductVO();
               allMenu = model.waterselectAll(vo);
               view.water();
               view.user_WaterMenu(allMenu); // 매운맛 목록
               if (view.action == allMenu.size() + 1) {
                  break; // 처음으로 돌아가기
               }
               vo.setNum(allMenu.get(view.action - 1).getNum());
               // 선택 매운 맛 저장
               resultVO.setWater(model.waterselectOne(vo).getWater());
               view.shoppingBasket();
            } while (model.waterselectOne(vo) == null);
            if (view.action == allMenu.size() + 1) {
            	ramenVO.setCnt(ramenVO.getCnt() + 1);	// 주문한 라면 메뉴 재고 원래대로
               view.back(); // 처음으로 돌아가기
               continue;
            }

            // 라면 선택 성공 --> 토핑 선택
            // -------------------------------------------------------------------
            vo = new ProductVO();
            vo.setNum(0); // PK변화가 없다면(num(PK) == 0) 미선택
            allMenu = model.topingselectAll(vo);


            // 토핑, 사이드메뉴는 사용자의 기호에 따라 미선택 가능(미선택 : 0번 입력)
            while (true) {// 재고 있는 메뉴 선택할 때까지 반복
               view.toping();
               view.user_TopingMenu(allMenu); // 토핑 목록 출력
               if (view.action == allMenu.size() + 1) {
                  // 처음으로 돌아가기
                  break;
               }
               // 토핑 고르고
               // 사용자 입력 --> PK로 전환 후 저장
               if (view.action == 0) { // 토핑 선택안했을 때 --> num(PK) == 0
                  resultVO.setToping("미선택");
                  break;
               }

               vo.setNum(allMenu.get(view.action - 1).getNum());
               vo.setCnt(-1); // 재고 -1 감소
               
               topingVO = model.topingselectOne(vo);	// 주문한 토핑
               if (topingVO.getCnt() > 0) { // 선택한 메뉴 재고 있으면 다음으로
                  // 선택 토핑 저장
                  resultVO.setToping(topingVO.getToping());
                  resultVO.setPrice(resultVO.getPrice() + topingVO.getPrice());
                  break;
               }
               view.soldOut(); // 재고 없는 메뉴 선택할 경우
               vo.setNum(0); // 번호, 재고 reset
            }

            if (!model.topingUpdate(vo)) { // 선택 토핑을 찾을 수 없는 경우
               if (view.action == allMenu.size() + 1) {
            	   ramenVO.setCnt(ramenVO.getCnt() + 1);	// 주문한 라면 메뉴 재고 원래대로
                  view.back(); // 처음으로 돌아가기
                  continue;
               } else if (!(vo.getNum() == 0)) { // 토핑 미선택도 아닌 경우
            	   ramenVO.setCnt(ramenVO.getCnt() + 1);	// 주문한 라면 메뉴 재고 원래대로
                  view.fail(); // 무엇인가 큰 오류 발생
                  continue;
               }
            }

            // 토핑 선택 성공 --> 사이드 메뉴
            // 선택--------------------------------------------------------------------
            view.shoppingBasket();

            vo = new ProductVO();
            vo.setNum(0); // PK변화가 없다면(num(PK) == 0) 미선택
            allMenu = model.sideselectAll(vo);


            // 토핑, 사이드메뉴는 사용자의 기호에 따라 미선택 가능(미선택 : 0번 입력)
            while (true) { // 재고 있는 메뉴 선택할 때까지 반복
               view.side();
               view.user_SideMenu(allMenu); // 사이드메뉴 목록 출력

               if (view.action == allMenu.size() + 1) {
                  // 처음으로 돌아가기
                  break;
               }

               if (view.action == 0) { // 사이드메뉴 선택안했을 때 --> num(PK) == 0
                  resultVO.setSide("미선택");
                  break;
               }
               // 사이드 메뉴 고르고
               // 사용자 입력 --> PK로 전환 후 저장
               vo.setNum(allMenu.get(view.action - 1).getNum());
               vo.setCnt(-1); // 재고 -1 감소
               
               sideVO = model.sideselectOne(vo);	// 주문한 사이드 메뉴
               if (sideVO.getCnt() > 0) { // 선택한 메뉴 재고 있으면 다음으로
                  // 선택 사이드 저장
                  resultVO.setSide(sideVO.getSide());
                  resultVO.setPrice(resultVO.getPrice() + sideVO.getPrice());
                  break;
               }
               view.soldOut(); // 재고 없는 메뉴 선택할 경우
               vo.setNum(0); // 번호, 재고 reset
            }

            if (!model.sideUpdate(vo)) { // 사이드 선택을 실패했는데
               if (view.action == allMenu.size() + 1) {
            	   ramenVO.setCnt(ramenVO.getCnt() + 1);	// 주문한 라면 메뉴 재고 원래대로
            	   topingVO.setCnt(topingVO.getCnt() + 1);	// 주문한 토핑 메뉴 재고 원래대로
                  view.back(); // 처음으로 돌아가기
                  continue;
               } else if (!(vo.getNum() == 0)) { // 사이드 미선택도 아닌 경우
            	   ramenVO.setCnt(ramenVO.getCnt() + 1);	// 주문한 라면 메뉴 재고 원래대로
            	   topingVO.setCnt(topingVO.getCnt() + 1);	// 주문한 토핑 메뉴 재고 원래대로
                  view.fail(); // 무엇인가 큰 오류 발생
                  continue; // --> 다시 처음으로
               }
            }
            // 사이드 메뉴 선택 성공 --> 주문 완료
            view.shoppingBasket();
            view.printDatas(resultVO); // 주문한 전체 메뉴 담은 장바구니, 가격 정보 안내

         }

         else if (view.action == 2) { // 2) 프로그램 종료
            view.end();
            break;
         
/////////////////////////////////////////////관리자모드/////////////////////////////////////////////////////
         
         }else if (view.action == 3) { // 관리자모드로 변경(비밀번호 입력 받을거임)
            Scanner sc = new Scanner(System.in);
            int i; // 비밀번호 틀린 횟수 카운트
            String passwordAnswer = "0607"; // 관리자모드 진입시 비밀번호값
            for (i = 4; i > 1; i--) {
               System.out.print("관리자모드 비밀번호 입력(" + (i - 1) + "회) : ");
               String password = sc.next();
               if (passwordAnswer.equals(password)) { // 비밀번호가 일치하다면
                  break;
               } else { // 비밀번호가 틀린다면
                  System.out.println("비밀번호가 일치하지 않습니다.");
                  continue;
               }
            }
            if (i == 1) {   // 3회이상 틀렸을 시 사용자모드로 이동
               System.out.println("3회이상 일치하지 않아 처음화면으로 이동합니다.");
               continue;
            }
            System.out.println("\n[ 관리자 모드 ] 에 입장합니다 :D");
            while (true) {
               view.adminView(); // 관리자모드 활성화
               if (view.action == 1) { // ◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆메뉴 추가◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆
                  view.admin_addMenu(); // 메뉴 추가 항목(라면, 토핑, 사이드, 관리자모드 종료)
                  if (view.action == 1) { // 라면항목을 선택했다면
                     ProductVO vo = new ProductVO();
                     view.admin_RamenMenu((model.ramenselectAll(vo)));   // 라면 목록 출력
                     System.out.print("라면 제품명 ");
                     String name = view.inputString(); // 라면이름 입력
                     if (name.equals(passwordAnswer)) { // 비밀번호 입력시 관리자모드 처음화면으로 이동
                        view.back();
                        continue;
                     }
                     System.out.print("재고 "); // 라면 이름 주기
                     int cnt = view.inputInt_Cnt(); // 재고 입력
                     if (cnt == Integer.parseInt(passwordAnswer)) {       // 비밀번호 입력시 관리자모드 처음화면으로 이동
                        view.back();
                        continue;
                     }
                     System.out.print("가격 "); // 라면 이름 주기
                     int price = view.inputInt_Price(); // 가격 입력
                     if (price == Integer.parseInt(passwordAnswer)) {   // 비밀번호 입력시 관리자모드 처음화면으로 이동
                        view.back();
                        continue;
                     }
                     view.admin_selectcheck(name); // 추가할건지 유효성 검사
                     String ans = view.scoreQ(); // 라면을 추가여부 YES / NO
                     if (ans.equals("Y")) {
                        if (model.ramenInsert(vo)) {
                           vo.setRamen(name);// 이름 저장
                           vo.setCnt(cnt); // 재고 수량 더하기 빼기 저장
                           vo.setPrice(price); // 가격 저장
                           view.success(); // 성공 출력문
                           view.admin_RamenMenu((model.ramenselectAll(vo)));   // 라면 목록 출력
                           System.out.println(" ");
                        }
                     } else {
                        view.fail(); // N입력시 실패 출력문
                     }
                  } else if (view.action == 2) {
                     ProductVO vo = new ProductVO();
                     view.admin_TopingMenu((model.topingselectAll(vo)));   // 토핑 목록 출력
                     System.out.print("토핑 제품명 ");
                     String name = view.inputString(); // 토핑이름 입력
                     if (name.equals(passwordAnswer)) {   // 비밀번호 입력 후 관리자모드 처음화면 이동
                        view.back();
                        continue;
                     }
                     System.out.print("재고 ");
                     int cnt = view.inputInt_Cnt(); // 재고 입력
                     if (cnt == Integer.parseInt(passwordAnswer)) {   // 비밀번호 입력 후 관리자모드 처음화면 이동
                        view.back();
                        continue;
                     }
                     System.out.print("가격 ");
                     int price = view.inputInt_Price(); // 가격 입력
                     if (price == Integer.parseInt(passwordAnswer)) {   // 비밀번호 입력 후 관리자모드 처음화면 이동
                        view.back();
                        continue;
                     }
                     view.admin_selectcheck(name); // 추가여부 유효성 검사
                     String ans = view.scoreQ(); // 토핑을 추가여부 YES / NO
                     if (ans.equals("Y")) {
                        if (model.topingInsert(vo)) {   // insert함수로 저장
                           vo.setToping(name); // 토핑 이름 저장
                           vo.setCnt(cnt); // 재고 수량 더하기 빼기 저장
                           vo.setPrice(price); // 가격 저장
                           view.success(); // 성공 출력문
                           view.admin_TopingMenu((model.topingselectAll(vo)));   // 토핑 목록 출력문
                           System.out.println(" ");
                        }
                     } else {
                        view.fail(); // N입력시 실패 출력문
                     }
                  } else if (view.action == 3) {
                     ProductVO vo = new ProductVO(); // 저장할 vo객체 생성
                     view.admin_SideMenu((model.sideselectAll(vo)));
                     System.out.print("사이드메뉴 제품명 ");
                     String name = view.inputString(); // 사이드메뉴 이름 입력
                     if (name.equals(passwordAnswer)) {   // 비밀번호 입력 후 관리자모드 처음화면 이동
                        view.back();
                        continue;
                     }
                     System.out.print("재고 ");
                     int cnt = view.inputInt_Cnt(); // 재고 입력
                     if (cnt == Integer.parseInt(passwordAnswer)) {   // 비밀번호 입력 후 관리자모드 처음화면 이동
                        view.back();
                        continue;
                     }
                     System.out.print("가격 ");
                     int price = view.inputInt_Price(); // 가격 입력
                     if (price == Integer.parseInt(passwordAnswer)) {   // 비밀번호 입력 후 관리자모드 처음화면 이동
                        view.back();   
                        continue;
                     }
                     view.admin_selectcheck(name); // 추가여부 유효성 검사
                     String ans = view.scoreQ(); // 사이드메뉴를 추가여부 YES / NO
                     if (ans.equals("Y")) {
                        if (model.sideInsert(vo)) {
                           vo.setSide(name); // 사이드메뉴 이름 저장
                           vo.setCnt(cnt); // 재고 수량 더하기 빼기 저장
                           vo.setPrice(price); // 가격 저장
                           view.success(); // 성공 출력문
                           view.admin_SideMenu((model.sideselectAll(vo)));   // 사이드메뉴 목록 출력
                           System.out.println(" ");
                        }
                     } else {
                        view.fail(); // N입력시 실패 출력문
                     }
                  } else if (view.action == 4) { // 관리자 모드 처음화면 이동
                     view.back();
                     continue;
                  }
               } else if (view.action == 2) { // ◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆메뉴 삭제◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆
                  view.admin_deleteMenu(); // 삭제할 항목 선택 1.라면 2.토핑 3.사이드 4.처음으로
                  if (view.action == 1) {
                     ProductVO vo = new ProductVO();
                     ArrayList<ProductVO> data = model.ramenselectAll(vo);
                     view.admin_RamenMenu(model.ramenselectAll(new ProductVO())); // 라면 목록 출력
                     if(data.size()==0) {   // 저장된 메뉴가 없다면 관리자모드 처음화면으로 이동
                        continue;
                     }
                     System.out.print("\n삭제하실 ");
                     int num = view.inputInt(model.ramenselectAll(new ProductVO())); // VO에게 줄 num변수 선언과 초기화
                     if (num == Integer.parseInt(passwordAnswer)) {   // 비밀번호 입력 후 관리자모드 처음화면 이동
                        view.back();   
                        continue;
                     }
                     vo.setNum(model.ramenselectAll(new ProductVO()).get(num - 1).getNum());
                     view.admin_deletecheck(num); // "num을 삭제하시겠습니까?"
                     String ans = view.scoreQ(); // 마지막으로 삭제할지 유효성 검사
                     if (ans.equals("Y")) {
                        model.ramenDelete(vo); // 삭제 함수 실행
                        view.success(); // 성공 출력문
                        view.admin_RamenMenu(model.ramenselectAll(new ProductVO())); // 라면 목록 출력
                     } else {
                        view.fail(); // N입력시 실패 출력문
                     }

                  } else if (view.action == 2) {
                     ProductVO vo = new ProductVO();
                     ArrayList<ProductVO> data = model.topingselectAll(vo);
                     view.admin_TopingMenu(model.topingselectAll(new ProductVO())); // 토핑 목록 출력
                     if(data.size()==0) {   // 저장된 메뉴가 없다면 관리자모드 처음화면으로 이동
                        continue;
                     }
                     System.out.print("삭제하실 ");
                     int num = view.inputInt(model.topingselectAll(new ProductVO())); // VO에게 줄 num변수 선언과 초기화
                     if (num == Integer.parseInt(passwordAnswer)) {   // 비밀번호 입력 후 관리자모드 처음화면 이동
                        view.back();   
                        continue;
                     }
                     vo.setNum(model.topingselectAll(new ProductVO()).get(num - 1).getNum());
                     view.admin_deletecheck(num); // "num을 삭제하시겠습니까?"
                     String ans = view.scoreQ(); // 마지막으로 삭제할지 유효성 검사
                     if (ans.equals("Y")) {
                        model.topingDelete(vo); // 삭제 함수 실행
                        view.success(); // 성공 출력문
                        view.admin_TopingMenu(model.topingselectAll(new ProductVO())); // 토핑 목록 출력
                     } else {
                        view.fail(); // N입력시 실패 출력문
                     }
                  } else if (view.action == 3) {
                     ProductVO vo = new ProductVO();
                     ArrayList<ProductVO> data = model.sideselectAll(vo);
                     view.admin_SideMenu(model.sideselectAll(new ProductVO())); // 사이드 목록 출력
                     if(data.size()==0) {   // 저장된 메뉴가 없다면 관리자모드 처음화면으로 이동
                        continue;
                     }
                     System.out.print("삭제하실 ");
                     int num = view.inputInt(model.sideselectAll(new ProductVO())); // VO에게 줄 num변수 선언과 초기화
                     if (num == Integer.parseInt(passwordAnswer)) {   // 비밀번호 입력 후 관리자모드 처음화면 이동
                        view.back();   
                        continue;
                     }
                     vo.setNum(model.sideselectAll(new ProductVO()).get(num - 1).getNum());
                     view.admin_deletecheck(num); // "num을 삭제하시겠습니까?"
                     String ans = view.scoreQ(); // 마지막으로 삭제할지 유효성 검사
                     if (ans.equals("Y")) {
                        model.sideDelete(vo); // 삭제 함수 실행
                        view.success(); // 성공 출력문
                        view.admin_SideMenu(model.sideselectAll(new ProductVO())); // 사이드 목록 출력
                     } else {
                        view.fail(); // N입력시 실패 출력문
                     }
                  } else if (view.action == 4) {   // 관리자모드 처음화면으로 이동
                     view.back();
                     continue;
                  }
               } else if (view.action == 3) { // ◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆재고 변경◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆
                  view.admin_updateCnt();
                  if (view.action == 1) {
                     while (true) {
                        view.admin_RamenMenu(model.ramenselectAll(new ProductVO()));   // 라면 목록 출력
                        ProductVO vo = new ProductVO();
                        ArrayList<ProductVO> data = model.ramenselectAll(vo);
                        if(data.size()==0) {   // 저장된 메뉴가 없다면 관리자모드 처음화면으로 이동
                            break;
                         }
                        System.out.print("변경하실 ");
                        int num = view.inputInt(model.ramenselectAll(new ProductVO()));   // 메뉴 목록의 범위는 정해져 있기 때문에
                        // 정해져 있는 범위 내에서 원하는 메뉴 번호를 입력 받기 위한 코드
                        if (num == Integer.parseInt(passwordAnswer)) {   // 비밀번호 입력 후 관리자모드 처음화면 이동
                           view.back();   
                           continue;
                        }
                        System.out.print("재고변경할 수량 ");
                        int cnt = view.inputInt(); // 재고는 범위 제한이 없으므로 일반 정수 타입으로 받기
                        if (cnt == Integer.parseInt(passwordAnswer)) {   // 비밀번호 입력 후 관리자모드 처음화면 이동
                           view.back();   
                           continue;
                        }
                        vo.setNum(model.ramenselectAll(new ProductVO()).get(num - 1).getNum());   // vo객체 저장
                        vo.setCnt(cnt); // vo객체 저장
                        if(model.ramenUpdate(vo)) {// 저장된 vo를 update함수로 보내기
                        	view.success(); // 성공 메소드 출력
                        } else {
                        	view.fail();
                        }
                        String ans = view.admin_check();    // 추가 할게 더 있는지 유효성 검사
                        if (ans.equals("Y")) {   // Y라면 바로 재고변경 반복문
                           if (true) {
                              continue;
                           }
                        } else {
                           view.admin_RamenMenu((model.ramenselectAll(vo))); // 추가 할게 없다면 최종적인 메뉴 출력
                           break;
                        }
                     }
                  } else if (view.action == 2) {
                     while (true) {
                        view.admin_TopingMenu(model.topingselectAll(new ProductVO()));   // 토핑 목록 출력
                        ProductVO vo = new ProductVO();
                        ArrayList<ProductVO> data = model.ramenselectAll(vo);
                        if(data.size()==0) {   // 저장된 메뉴가 없다면 관리자모드 처음화면으로 이동
                            break;
                         }
                        System.out.print("변경하실 ");
                        int num = view.inputInt(model.topingselectAll(new ProductVO()));// 메뉴 목록의 범위는 정해져 있기 때문에
                        // 정해져 있는 범위 내에서 원하는 메뉴 번호를 입력 받기 위한 코드
                        if (num == Integer.parseInt(passwordAnswer)) {   // 비밀번호 입력 후 관리자모드 처음화면 이동
                           view.back();   
                           continue;
                        }
                        System.out.print("재고변경할 수량 ");
                        int cnt = view.inputInt();   // 재고는 범위 제한이 없으므로 일반 정수 타입으로 받기
                        if (cnt == Integer.parseInt(passwordAnswer)) {   // 비밀번호 입력 후 관리자모드 처음화면 이동
                           view.back();   
                           continue;
                        }
                        vo.setNum(model.topingselectAll(new ProductVO()).get(num - 1).getNum());
                        // vo.setNum(num);
                        vo.setCnt(cnt);
                        if(model.topingUpdate(vo)) {// 저장된 vo를 update함수로 보내기
                        	view.success(); // 성공 메소드 출력
                        } else {
                        	view.fail();
                        }
                        String ans = view.admin_check();   // 추가 할게 더 있는지 유효성 검사
                        if (ans.equals("Y")) {   // Y라면 바로 재고변경 반복문
                           if (true) {
                              continue;
                           }
                        } else {
                           view.admin_TopingMenu((model.topingselectAll(vo)));   // 추가 할게 없다면 최종적인 메뉴 출력
                           break;
                        }
                     }
                  } else if (view.action == 3) {
                     while (true) {
                        view.admin_SideMenu(model.sideselectAll(new ProductVO()));
                        ProductVO vo = new ProductVO();
                        ArrayList<ProductVO> data = model.ramenselectAll(vo);
                        if(data.size()==0) {   // 저장된 메뉴가 없다면 관리자모드 처음화면으로 이동
                            break;
                         }
                        System.out.print("변경하실 ");
                        int num = view.inputInt(model.sideselectAll(new ProductVO()));// 메뉴 목록의 범위는 정해져 있기 때문에
                        // 정해져 있는 범위 내에서 원하는 메뉴 번호를 입력 받기 위한 코드
                        if (num == Integer.parseInt(passwordAnswer)) {   // 비밀번호 입력 후 관리자모드 처음화면 이동
                           view.back();   
                           continue;
                        }
                        System.out.print("재고변경할 수량 ");
                        int cnt = view.inputInt();   // 재고는 범위 제한이 없으므로 일반 정수 타입으로 받기
                        if (cnt == Integer.parseInt(passwordAnswer)) {   // 비밀번호 입력 후 관리자모드 처음화면 이동
                           view.back();   
                           continue;
                        }
                        vo.setNum(model.sideselectAll(new ProductVO()).get(num - 1).getNum());
                        // vo.setNum(num);
                        vo.setCnt(cnt);if(model.sideUpdate(vo)) {// 저장된 vo를 update함수로 보내기
                        	view.success(); // 성공 메소드 출력
                        } else {
                        	view.fail();
                        }
                        String ans = view.admin_check();   // 추가 할게 더 있는지 유효성 검사
                        if (ans.equals("Y")) {   // Y라면 바로 재고변경 반복문
                           if (true) {
                              continue;
                           }
                        } else {
                           view.admin_SideMenu((model.sideselectAll(vo)));   // 추가 할게 없다면 최종적인 메뉴 출력
                           break;
                        }
                     }
                  } else if (view.action == 4) {   // 관리자모드 처음화면으로 이동
                     view.back();
                     continue;
                  }
               } else if (view.action == 4) { // 관리자모드종료
                  view.admin_end(); // 사용자모드로 전환된 모습
                  view.back();
                  break;
               }

            }
         }
      }
   }

}