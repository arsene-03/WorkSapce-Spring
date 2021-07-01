package spring.main;

import java.util.Scanner;

import spring.assembler.Assembler;
import spring.dao.MemberDAO;
import spring.exaption.AlreadyExistionMemberException;
import spring.exaption.IdPasswordNotMatchingException;
import spring.exaption.MemberNotFoundExcaption;
import spring.service.ChangePasswordService;
import spring.service.MemberRegisterService;
import spring.vo.RegisterRequest;

public class Main01 {

	public static Scanner scan = new Scanner(System.in);
	public static Assembler assem = new Assembler();
	
	public static void main(String[] args) {
		// 뷰 서블릿을 호출하는 역할
		
		while (true) {
			System.out.println("명령어를 입력하세요");
			String command = scan.nextLine();
			
			if(command.equalsIgnoreCase("exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}else if(command.equalsIgnoreCase("new")) {
				processNewCommand();
				continue;
			}else if(command.equalsIgnoreCase("change")) {
				processChangeCommand();
				continue;
			}else {
				printHelp();
			}
			
		}

	}

	private static void processChangeCommand() {
		System.out.println("비밀번호를 번경합니다.");
		System.out.println("이메일을 입력해주세요");
		String email = scan.nextLine();
		System.out.println("기존 비밀번호를 입력해주세요");
		String oldPassword = scan.nextLine();
		System.out.println("새로운 비밀번호를 입력해주세요");
		String newPassword = scan.nextLine();
		
		ChangePasswordService changePwdSvc = assem.getPwdSvc();//new ChangePasswordService(new MemberDAO());
		
		try {
			changePwdSvc.changePassword(email, oldPassword, newPassword);
			System.out.println("비밀번호 변경이 완료되었습니다.");
		} catch (MemberNotFoundExcaption e) {
			System.out.println("존재하지 않는 이메일 입니다.");
		} catch (IdPasswordNotMatchingException e) {
			System.out.println("이메일과 암호가 일치하지 않습니다.");
		}
		
		
	}

	private static void processNewCommand() {
		RegisterRequest req = new RegisterRequest();
		
		System.out.println("회원가입을 하겠습니다.");
		System.out.println("이메일 주소를 입력하세요. : ");
		req.setEmail(scan.nextLine());
		System.out.println("비밀번호를 입력하세요. : ");
		req.setPassword(scan.nextLine());
		System.out.println("비밀번호를 다시 입력해주세요. : ");
		req.setConfirmPassword(scan.nextLine());
		System.out.println("이름을 입력해 주세요. : ");
		req.setName(scan.nextLine());
		
		if(!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("비밀번호와 확인비밀번호가 일치하지 않습니다.");
			return;
		}
		MemberRegisterService regSvs = assem.getRegSvc();//new MemberRegisterService();
		try {
			regSvs.regist(req);
			System.out.println("회원가입이 완료되었습니다.");
		} catch (AlreadyExistionMemberException e) {
			System.out.println("이미 존재하는 이메일 입니다.");
		}
		
	}

	private static void printHelp() {
		System.out.println();
		System.out.println("잘못된 명령어입니다. 아래의 명령어를 확인해 주세요");
		System.out.println("----명령어 사용법----");
		System.out.println("new : 회원가입");
		System.out.println("change : 비밀번호변경");
		System.out.println();
		
	}

}
