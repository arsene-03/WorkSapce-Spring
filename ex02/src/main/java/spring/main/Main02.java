package spring.main;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import spring.exaption.AlreadyExistionMemberException;
import spring.exaption.IdPasswordNotMatchingException;
import spring.exaption.MemberNotFoundExcaption;
import spring.printer.MemberInfoPrinter;
import spring.printer.MemberListPrinter;
import spring.printer.VersionPrinter;
import spring.service.ChangePasswordService;
import spring.service.MemberRegisterService;
import spring.vo.RegisterRequest;

public class Main02 {


		public static Scanner scan = new Scanner(System.in);
		public static ApplicationContext ctx = null;
		
		public static void main(String[] args) {
			//ctx = new GenericXmlApplicationContext("calsspath:appContext01.xml"); //스프링 컨테이너 불러오기
			
			//=> 하나로 합친 파일 가져오기
			//ctx = new GenericXmlApplicationContext("classpath:Ctx01.xml");  

			//=> 두파일을 따로 가져오기
			String[] conf = {"classpath:Ctx01.xml","classpath:Ctx02.xml"};
			ctx = new GenericXmlApplicationContext(conf);
			
			
			
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
				}else if(command.equalsIgnoreCase("info")) {
					processInfoCommand();
					continue;
				}else if(command.equalsIgnoreCase("list")) {
					processListCommand();
					continue;
				}else if(command.equalsIgnoreCase("version")) {
					processVersionCommand();
					continue;
				}else {
					printHelp();
				}
				
			}

		}

		private static void processVersionCommand() {
			VersionPrinter versionPrinter = ctx.getBean("versionPrinter", VersionPrinter.class);
			versionPrinter.print();
			
		}

		private static void processListCommand() {
			System.out.println("전체 회원 정보를 출력합니다.");
			MemberListPrinter listprinter = ctx.getBean("listprinter", MemberListPrinter.class);
			listprinter.printAll();
			
		}

		private static void processInfoCommand() {
			System.out.println("조회하고자 하는 이메일을 입력해주세요.");
			String email = scan.nextLine();
			
			MemberInfoPrinter infoPrinter = ctx.getBean("infoPrinter", MemberInfoPrinter.class);
			infoPrinter.printMemterInfo(email);
			
		}

		private static void processChangeCommand() {
			System.out.println("비밀번호를 번경합니다.");
			System.out.println("이메일을 입력해주세요");
			String email = scan.nextLine();
			System.out.println("기존 비밀번호를 입력해주세요");
			String oldPassword = scan.nextLine();
			System.out.println("새로운 비밀번호를 입력해주세요");
			String newPassword = scan.nextLine();
			
			ChangePasswordService changePwdSvc = //new ChangePasswordService(new MemberDAO());
													//assem.getPwdSvc();
													ctx.getBean("ChangePwdSvc", ChangePasswordService.class);
					
			
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
			MemberRegisterService regSvs = //new MemberRegisterService();
											//assem.getRegSvc();
											ctx.getBean("memberRegSvc", MemberRegisterService.class);
			
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
			System.out.println("info : 회원정보조회");
			System.out.println("list : 전체회원정보조회");
			System.out.println("version : 프로그램 버전정보 조회");
			System.out.println();
			
		}


}


