package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.ProductVO;

public class ProductView{
   Scanner sc = new Scanner(System.in);
   public int action; //사용자가 입력할 변수

   //사용자모드 첫 화면
   public void startView() {
      while(true) {
         System.out.println("============ Ramen Machine =========");
         System.out.println("[1] 메뉴선택 [2] 종료 [3] 관리자모드");
         System.out.print("입력 : ");
         try {
            action = sc.nextInt();
            if(1<=action && action <=3) {
               break;
            }
            System.out.println("다시 입력해주세요!");
            System.out.println();
         }catch(Exception e) {
            sc.nextLine();
            System.out.println("메뉴 항목 중에 선택하세요!");
            System.out.println();
         }
      }
   }

   //문자열 입력
   public String inputString() {
      while(true) {
         try {
            System.out.print("입력 : ");
            String str = sc.next();
            return str;
         }catch(Exception e) {
            sc.nextLine();
            System.out.println("다시 입력해주세요");
            System.out.println();
         }
      }
   }

   //정수형 입력 
   public int inputInt() {   //기본 정수입력
      while(true) {
         System.out.print("입력 : ");
         try {
            int num = sc.nextInt();
            return num;
         }catch(Exception e) {
            sc.nextLine();
            System.out.println("다시 입력해주세요");
         }
      }
   }
   public int inputInt_Cnt() {   // 메뉴 추가할 때 필요한 재고입력창(음수가존재하면 안된다.)
      while(true) {
         System.out.print("재고입력 : ");
         try {
            int num = sc.nextInt();
            if(num > 0) {
               return num;
            }
            System.out.println("음수는 입력받을 수 없습니다.\n");
         }catch(Exception e) {
            sc.nextLine();
            System.out.println("다시 입력해주세요");
         }
      }
   }
   public int inputInt_Price() {   // 메뉴 추가할 때 필요한 가격입력창(음수가 존재하면 안된다.)
      while(true) {
         System.out.print("가격입력 : ");
         try {
            int num = sc.nextInt();
            if(num > 0) {
               return num;
            }
            System.out.println("음수는 입력받을 수 없습니다.\n");
         }catch(Exception e) {
            sc.nextLine();
            System.out.println("다시 입력해주세요");
         }
      }
   }
   public int inputInt(ArrayList<ProductVO> vo) {   // 메뉴삭제, 재고변경 할 때 범위에 초과된 번호를 입력 받으면 안된다.
      while(true) {
         System.out.print("메뉴 번호입력 : ");
         try {
            int num = sc.nextInt();
            if(num<1 || num>vo.size()) {
               System.out.println("범위 외 입력\n");
               continue;
            }
            return num;
         }catch(Exception e) {
            sc.nextLine();
            System.out.println("다시 입력해주세요");
            System.out.println();
         }
      }
   }

   // 사용자모드_라면 메뉴 선택 ----------------------------------------
   public void user_RamenMenu(ArrayList<ProductVO> data) { // 이걸로 수정해야함 
      if(data.size() == 0) {
         System.out.println("죄송합니다. 현재 메뉴 준비중입니다.");
         System.out.println();
//         return;
      }
      for(int i = 0; i < data.size(); i++) {
         if(data.get(i).getCnt() <= 0) {
            System.out.println((i+1)+"번 : "+data.get(i).getRamen() + "\t품절");
            continue;
         }
         System.out.println((i+1)+"번 : "+data.get(i).getRamen() + "\t" + data.get(i).getPrice() + "원");
      }
      System.out.println(data.size() + 1 + "번 : 처음으로 돌아가기");

      while(true) {
         System.out.print("메뉴번호 입력 : ");
         try { 
            action=sc.nextInt();
            if (1<=action &&action<=data.size() + 1) {
               break;
            }
            System.out.println("번호 확인후 다시입력해주세요!");
            System.out.println();
         } catch (Exception e) {
            sc.nextLine();
            System.out.println("정수만 입력해주세요!");
            System.out.println();
         }
      }
   }

   // 사용자모드_토핑, 사이드 메뉴 선택 ---------------------------------
   public void user_TopingMenu(ArrayList<ProductVO> data){
      if(data.size() == 0) {
         System.out.println("죄송합니다. 현재 메뉴 준비중입니다.");
         System.out.println();
//         return;
      }
      for(int i = 0; i < data.size(); i++) {
         if(data.get(i).getCnt() <= 0) {
            System.out.println((i+1)+"번 : "+data.get(i).getToping() + "\t품절"); // ? 
            continue;
         }
         System.out.println((i+1)+"번 : "+data.get(i).getToping() + "\t" + data.get(i).getPrice() + "원");
      }
      System.out.println(data.size() + 1 + "번 : 처음으로 돌아가기");
      while(true) {
         System.out.print("메뉴번호 입력 : ");
         try { 
            action=sc.nextInt();    // 0번 입력 --> 선택 안함
            if (0<=action &&action<=data.size()+1) {
               break;
            }
            System.out.println("상품번호 확인후 다시입력해주세요!");
            System.out.println();
         } catch (Exception e) {
            sc.nextLine();
            System.out.println("정수만 입력해주세요!");
            System.out.println();
         }
      }
   }
   public void user_SideMenu(ArrayList<ProductVO> data){
      if(data.size() == 0) {
         System.out.println("죄송합니다. 현재 메뉴 준비중입니다.");
         System.out.println();
//         return;
      }
      for(int i = 0; i < data.size(); i++) {
         if(data.get(i).getCnt() <= 0) {
            System.out.println((i+1)+"번 : "+data.get(i).getSide() + "\t품절"); // ? 
            continue;
         }
         System.out.println((i+1)+"번 : "+data.get(i).getSide() + "\t" + data.get(i).getPrice() + "원");
      }
      System.out.println(data.size() + 1 + "번 : 처음으로 돌아가기");
      while(true) {
         System.out.print("메뉴번호 입력 : ");
         try { 
            action=sc.nextInt();    // 0번 입력 --> 선택 안함
            if (0<=action &&action<=data.size()+1) {
               break;
            }
            System.out.println("상품번호 확인후 다시입력해주세요!");
            System.out.println();
         } catch (Exception e) {
            sc.nextLine();
            System.out.println("정수만 입력해주세요!");
            System.out.println();
         }
      }
   }

   // 사용자모드_매운 맛, 물의 양 메뉴 선택 ---------------------------------
   public void user_WaterMenu(ArrayList<ProductVO> data) {
      for(int i = 0; i < data.size(); i++) {
         System.out.println((i+1)+"번 : "+data.get(i).getWater()); 
      }
      System.out.println(data.size() + 1 + "번 : 처음으로 돌아가기");
      while(true) {
         System.out.print("메뉴번호 입력 : ");
         try { 
            action=sc.nextInt();
            if (1<=action &&action<=data.size()+1) {
               break;
            }
            System.out.println("상품번호 확인후 다시입력해주세요!");
            System.out.println();
         } catch (Exception e) {
            sc.nextLine();
            System.out.println("정수만 입력해주세요!");
            System.out.println();
         }
      }
   }

   public void user_SpicyMenu(ArrayList<ProductVO> data) {
      for(int i = 0; i < data.size(); i++) {
         System.out.println((i+1)+"번 : "+data.get(i).getSpicy()); 
      }
      System.out.println(data.size() + 1 + "번 : 처음으로 돌아가기");
      while(true) {
         System.out.print("메뉴번호 입력 : ");
         try { 
            action=sc.nextInt();
            if (1<=action &&action<=data.size()+1) {
               break;
            }
            System.out.println("상품번호 확인후 다시입력해주세요!");
            System.out.println();
         } catch (Exception e) {
            sc.nextLine();
            System.out.println("정수만 입력해주세요!");
            System.out.println();
         }
      }
   }

   // 사용자모드_메뉴선택 후 선택 안내 문구 (라면, 토핑, 사이드) 
   public void ramen() { 
      System.out.println();
      System.out.println("====== 라면 선택 ======");
   }

   public void toping() { // 토핑은 선택 안함이 가능함 
      System.out.println();
      System.out.println("=======토핑 선택=======");
      System.out.println("[0] 선택 안함");
   }

   public void side()  {  // 상디ㅡ은 선택 안함이 가능함 
      System.out.println();
      System.out.println("======사이드 선택======");
      System.out.println("[0] 선택 안함");
   }
   public void water() { 
      System.out.println();
      System.out.println("===== 몰의 양 선택 =====");
   }
   public void spicy() { 
      System.out.println();
      System.out.println("==== 맵기 단계 선택 ====");
   }


   //종료하기
   public void end() {
      System.out.println();
      for(int i=0;i<5;i++) {
         System.out.print(".");
         try {
            Thread.sleep(1000); // 1000==1초
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      System.out.println("프로그램 종료합니다.");
   }
   public void back() {
      for (int i = 0; i < 3; i++) {
         System.out.print(".");
         try {
            Thread.sleep(1000); // 1000==1초
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      System.out.println("처음화면으로 이동합니다.");
      System.out.println();
      for (int i = 0; i < 3; i++) {
         try {
            Thread.sleep(1000); // 1000==1초
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
   }


 //최종 주문 멘트(=주문서)
   public void printDatas(ProductVO vo) { 
      System.out.println("============= 주문서 ==============");
      System.out.println("라면\t : "+vo.getRamen());   
      System.out.println("토핑\t : " +vo.getToping());   
      System.out.println("맛\t : " + vo.getSpicy());   
      System.out.println("물의 양\t : "+vo.getWater());   
      System.out.println("사이드\t : "+vo.getSide());
      System.out.println("---------------------------------");
      System.out.println("\t\t   총 금액 : "+vo.getPrice()+"원");
      System.out.println("=================================");
      System.out.println();
   }
//   //처음 화면 돌아가기 멘트
//   public void goFirst() {
//	   System.out.println();
//      System.out.println("처음으로 돌아갑니다.");
//      System.out.println();
//   }
   //장바구니 추가 완료 멘트
   public void shoppingBasket() {
	   System.out.println();
      System.out.println("장바구니 추가 완료!");
      System.out.println();
   }
   //메뉴 준비중 멘트
   public void readyMenu() {
	   System.out.println();
      System.out.println("죄송합니다.현재 메뉴 준비중입니다ㅠㅠ ");
      System.out.println();
   }



   //매진된 품목 안내 멘트 
   public void soldOut() {
      System.out.println("매진된 품목입니다ㅠ^ㅠ 다른 메뉴를 선택해주세요.");
      System.out.println();
   }

   //---------------------------관리자모드---------------------

   public void adminView() {
      while(true) {
         System.out.println("======================= 관리자 모드 ==========================");
         System.out.println("[1] 메뉴 추가 [2] 메뉴 삭제 [3] 재고 변경 [4] 관리자모드 종료 ");
         System.out.print("관리자 메뉴 선택 : ");
         try {
            action = sc.nextInt();
            if(1<=action && action <=4) {
            	System.out.println();
               break;
            }
            System.out.println("잘못된 입력입니다. 다시 입력해주세요! (1~4) ");
         }catch(Exception e) {
            sc.nextLine();
            System.out.println("번호를 입력해주세요!");
         }
      }
   }


   //관리자모드_메뉴 추가
   public void admin_addMenu() {
      while(true) {
         System.out.println("-------------------------------------------------------------");
         System.out.println("[1] 라면 [2] 토핑 [3] 사이드메뉴 [4] 관리자모드 처음화면 이동");
         System.out.print("추가하실 항목 선택(각 항목당 비밀번호 입력시 처음화면 이동) : ");
         try {
            action = sc.nextInt();
            if(1<=action && action <=4) {
            	System.out.println();
               break;
            }
            System.out.println("잘못된 입력입니다. 다시 입력해주세요! (1~4)\n");
         }catch(Exception e) {
            sc.nextLine();
            System.out.println("번호를 입력해주세요!\n");
         }
      }
   }
   //관리자모드 메뉴 추가 확인창
   public void admin_selectcheck(String check) {
      System.out.println(check+"을 추가하시겠습니까?");
   }

   //관리자모드_메뉴 삭제
   public void admin_deleteMenu() {
      while(true) {
         System.out.println("-------------------------------------------------------------");
         System.out.println("[1] 라면 [2] 토핑 [3] 사이드메뉴 [4] 관리자모드 처음화면 이동");
         System.out.print("삭제하실 항목 선택 : ");
         try {
            action = sc.nextInt();
            if(1<=action && action <=4) {
            	System.out.println();
               break;
            }
            System.out.println("다시 입력해주세요!");
         }catch(Exception e) {
            sc.nextLine();
            System.out.println("번호를 입력해주세요!");
         }
      }
   }
   //관리자모드 메뉴 삭제 확인창
   public void admin_deletecheck(int check) {
      System.out.println(check+"번 메뉴를 삭제하시겠습니까?");
   }

   //관리자모드_재고 변경
   public void admin_updateCnt() {
      while(true) {
         System.out.println("-------------------------------------------------------------");
         System.out.println("[1] 라면 [2] 토핑 [3] 사이드메뉴 [4] 관리자모드 처음화면 이동");
         System.out.print("재고 변경하실 항목 선택 : ");
         try {
            action = sc.nextInt();
            if(1<=action && action <=4) {
            	System.out.println();
               break;
            }
            System.out.println("다시 입력해주세요!");
         }catch(Exception e) {
            sc.nextLine();
            System.out.println("번호를 입력해주세요!");
         }
      }
   }
   // 관리자 모드 라면 목록만 출력 + 재고 추가했음
   public void admin_RamenMenu(ArrayList<ProductVO> data) {
      if (data.size() == 0) {
         System.out.println("저장된 메뉴가 없습니다.");
         System.out.println();
         return;
      }
      for (int i = 0; i < data.size(); i++) {
         if (data.get(i).getCnt() <= 0) {
            System.out.println((i + 1) + "번 : " + data.get(i).getRamen() + "X");
            continue;
         }
         System.out.println((i + 1) + "번 : " + data.get(i).getRamen() + "[" + data.get(i).getPrice() + "원]" + " ---> 재고[" + data.get(i).getCnt() + "]");
      }
      System.out.println();
   }
   // 관리자 모드 토핑 목록만 출력 + 재고추가했음
   public void admin_TopingMenu(ArrayList<ProductVO> data) {
      if (data.size() == 0) {
         System.out.println("저장된 메뉴가 없습니다.");
         System.out.println();
         return;
      }
      for (int i = 0; i < data.size(); i++) {
         if (data.get(i).getCnt() <= 0) {
            System.out.println((i + 1) + "번 : " + data.get(i).getToping() + "X");
            continue;
         }
         System.out.println((i + 1) + "번 : " + data.get(i).getToping() + "[" + data.get(i).getPrice() + "원]" + " ---> 재고[" + data.get(i).getCnt() + "]");
      }
      System.out.println();
   }
   // 관리자 모드 사이드 목록만 출력 + 재고추가햇음
   public void admin_SideMenu(ArrayList<ProductVO> data) {
      if (data.size() == 0) {
         System.out.println("저장된 메뉴가 없습니다.");
         System.out.println();
         return;
      }
      for (int i = 0; i < data.size(); i++) {
         if (data.get(i).getCnt() <= 0) {
            System.out.println((i + 1) + "번 : " + data.get(i).getSide() + "X");
            continue;
         }
         System.out.println((i + 1) + "번 : " + data.get(i).getSide() + "[" + data.get(i).getPrice() + "원]" + " ---> 재고[" + data.get(i).getCnt() + "]");
      }
      System.out.println();
   }

   public String scoreQ() {
      String ans;
      while (true) {
         System.out.print("Y/N : ");
         ans = sc.next();
         ans = ans.toUpperCase();
         if (ans.equals("Y") || ans.equals("N")) {
            break;
         }
         System.out.println("Y or N만 입력 가능합니다.");
         System.out.println();
      }
      return ans;
   }

   //재고 변경 -> 추가 항목 확인
   public String admin_check() {
      String ans;
      while(true) {
         System.out.print("변경하실 항목이 더 있으십니까?\nY/N : ");
         ans = sc.next();
         ans = ans.toUpperCase(); //대문자로 변경
         if(ans.equals("Y") || ans.equals("N")){
            break;
         }
         System.out.println("Y or N만 입력 가능합니다!");
         System.out.println();
      }
      System.out.println();
      return ans;
   }

   //관리자모드_프로그램 종료
   public void admin_end() {
      System.out.println("관리자모드를 종료합니다.");
   }

   //수행완료
   public void success() {
      System.out.println("수행 완료했습니다.\n");
   }
   //수행실패
   public void fail() {
      System.out.println("수행 실패했습니다.\n");
   }
}