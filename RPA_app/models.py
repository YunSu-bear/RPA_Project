from django.db import models

# 원가 데이터
class Inje_Cost(models.Model):
    Data_Number = models.AutoField(primary_key=True)  # 순서, 자동 증가
    Mold_Number = models.CharField(max_length=50, null=False)  # 금형번호
    Product_Name = models.CharField(max_length=50, null=False)  # 품명
    Texture = models.CharField(max_length=50, null=False)  # 재질
    Grade = models.CharField(max_length=50, null=False)  # 그레이드
    Product_Weight = models.IntegerField(null=False)  # 제품중량
    SR_Weight = models.IntegerField(null=False)  # SR 중량
    B_Gross_Weight = models.FloatField(null=True)  # 개당총중량, 기본값 없음
    B_Raw_Material_Price = models.IntegerField(null=True)  # 원자재단가, 기본값 없음
    B_Raw_Material_Cost = models.FloatField(null=True)  # 개당원재료비, 기본값 없음
    Bright_tone = models.FloatField(null=False)  # 형체력
    Cav = models.IntegerField(null=False)  # 제품취출개수
    C_T = models.IntegerField(null=False)  # 공정시간
    B_Daily_Production = models.IntegerField(null=True)  # 일 생산량, 기본값 없음
    B_Facility_Utilization_Rate = models.FloatField(null=True)  # 설비임률, 기본값 없음
    B_Per_Unit_Utilization_Cost = models.FloatField(null=True)  # 개당임률, 기본값 없음
    B_Per_Unit_Cost = models.FloatField(null=True)  # 개당원가, 기본값 없음
    B_General_Management_Cost = models.FloatField(null=True)  # 일반관리비, 기본값 없음
    B_Defective_Loss = models.FloatField(null=True)  # 불량로스, 기본값 없음
    B_Corporate_Profit = models.FloatField(null=True)  # 기업이윤, 기본값 없음
    B_Sum_Cost = models.FloatField(null=True)  # 판매단가, 기본값 없음
    
    class Meta:
        db_table = 'Inje_Cost'
    # 계산
    def save(self, *args, **kwargs):
    # 원자재 단가 계산
        def Cost_action(Texture, Grade):
            if Texture and Grade:
                if Texture == 'ABS':
                    if Grade == 'HI121':
                        return 2400
                    else:
                        return 2600
                elif Texture == 'PP': 
                    if Grade == 'J-550A':
                        return 1800
                    else:
                        return 1600
                elif Texture == 'PE':
                    if Grade == 'M850':
                        return 1700
                    else:
                        return 2100
                else:
                    return 2300
            return 0
        
        # 설비 임률 계산
        def Daily_Pro(Bright_tone):
            rate_list = {
                100: 160000,
                120: 180000,
                130: 200000,
                150: 250000,
                160: 250000,
                170: 250000,
                180: 270000,
                200: 300000,
                220: 300000,
                240: 320000,
                280: 350000,
                380: 400000,
                400: 450000
            }
            return rate_list.get(Bright_tone, 0)
        
        # 계산 수행
        if self.Cav != 0:
            self.B_Gross_Weight = round((self.Product_Weight + self.SR_Weight) / self.Cav, 2)
        else:
            self.B_Gross_Weight = 0
        
        self.B_Raw_Material_Price = Cost_action(self.Texture, self.Grade)
        self.B_Raw_Material_Cost = round(self.B_Raw_Material_Price / 1000 * self.B_Gross_Weight, 2)
        
        self.B_Facility_Utilization_Rate = Daily_Pro(self.Bright_tone)
        
        if self.C_T != 0:
            self.B_Daily_Production = round((79200 / self.C_T) * self.Cav, 2)
        else:
            self.B_Daily_Production = 0
        
        if self.B_Daily_Production != 0:
            self.B_Per_Unit_Utilization_Cost = round(self.B_Facility_Utilization_Rate / self.B_Daily_Production, 2)
        else:
            self.B_Per_Unit_Utilization_Cost = 0
        
        self.B_Per_Unit_Cost = round(self.B_Raw_Material_Cost + self.B_Per_Unit_Utilization_Cost, 2)
        
        self.B_General_Management_Cost = round((self.B_Raw_Material_Cost + self.B_Per_Unit_Utilization_Cost) * 0.1, 2)
        self.B_Defective_Loss = round((self.B_Raw_Material_Cost + self.B_Per_Unit_Utilization_Cost + self.B_General_Management_Cost) * 0.1, 2)
        self.B_Corporate_Profit = round((self.B_Raw_Material_Cost + self.B_Per_Unit_Utilization_Cost + self.B_General_Management_Cost + self.B_Defective_Loss) * 0.1, 2)
        
        self.B_Sum_Cost = round(self.B_Raw_Material_Cost + self.B_Per_Unit_Utilization_Cost + self.B_General_Management_Cost + self.B_Defective_Loss + self.B_Corporate_Profit, 2)
        
        # 부모 클래스의 save 메서드를 호출하여 데이터베이스에 저장합니다.
        super(Inje_Cost, self).save(*args, **kwargs)

    def __str__(self):
        return self.Mold_Number

# 사용자 입력 데이터
class Inje_Cost_Bile(models.Model):
    Bile_ID = models.AutoField(primary_key=True)
    Bile_Data_Number = models.IntegerField() 
    Injection_Equipment = models.CharField(max_length=50)  # 사출설비
    Extraction_Method = models.CharField(max_length=50)  # 취출방식
    Operating_Time = models.CharField(max_length=50)  # 가동시간
    Packaging_Method = models.CharField(max_length=50)  # 포장방법
    Date_of_Preparation = models.DateField(max_length=50)  # 작성일자
    Specification = models.CharField(max_length=50)  # 규격
    Raw_Materials_Used = models.CharField(max_length=50)  # 사용 원자재
    Process_Name = models.CharField(max_length=50)  # 공정명
    Remarks_1 = models.CharField(max_length=255, null=True, blank=True)  # 비고1
    Remarks_2 = models.CharField(max_length=255, null=True, blank=True)  # 비고2
    Remarks_3 = models.CharField(max_length=255, null=True, blank=True)  # 비고3
    Remarks_4 = models.CharField(max_length=255, null=True, blank=True)  # 비고4
    Remarks_5 = models.CharField(max_length=255, null=True, blank=True)  # 비고5
    General_Management_Rate = models.IntegerField()  # 일반 관리비율
    Defective_Rate = models.IntegerField()  # 불량률
    Profit_Rate = models.IntegerField()  # 이윤율
    Other = models.CharField(max_length=255, null=True, blank=True)  # 기타
    Final_Estimate = models.IntegerField(null=True, blank=True)  # 최종 견적가
    Supplier_Estimate = models.IntegerField(null=True, blank=True)  # 업체 결정가
    
    class Meta:
        db_table = 'Inje_Cost_Bile'
        
    def __str__(self):
        return f"Inje_Cost_Bile {self.Bile_ID}"