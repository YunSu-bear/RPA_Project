from django import forms
from .models import Inje_Cost, Inje_Cost_Bile


# DataBase 원가 데이터 적재
class InjeCostForm(forms.ModelForm):
    TEXTURE_CHOICES = [
    ('ABS', 'ABS'),
    ('PP', 'PP'),
    ('PE', 'PE'),
    ('SAN', 'SAN'),
    ]
    GRADE_CHOICES = [
        ('HF380', 'HF380'), ('HI121', 'HI121'), ('SG175', 'SG175'),
        ('344RK', '344RK'), ('4017', '4017'), ('J-550A', 'J-550A'), ('J-560S', 'J-560S'),
        ('M850', 'M850'), ('5321', '5321'), ('XJ700', 'XJ700'),
        ('80HF', '80HF'), ('82TR', '82TR')
    ]
    Texture = forms.ChoiceField(choices=TEXTURE_CHOICES)
    Grade = forms.ChoiceField(choices=GRADE_CHOICES)
    
    class Meta:
        model = Inje_Cost
        exclude = [ 'B_Gross_Weight', 'B_Raw_Material_Price', 'B_Raw_Material_Cost',
                    'B_Per_Unit_Utilization_Cost', 'B_Per_Unit_Cost', 'B_General_Management_Cost',
                    'B_Defective_Loss', 'B_Corporate_Profit', 'B_Sum_Cost' ]
        fields = ['Mold_Number', 'Product_Name', 'Texture', 'Grade', 'Product_Weight', 'SR_Weight', 'Bright_tone', 'Cav', 'C_T']

# DataBase 사용자 입력 데이터 적재
class InjeCostBileForm(forms.ModelForm):
    class Meta:
        model = Inje_Cost_Bile
        exclude = [ 'Bile_Data_Number' ]
        fields = [
            'Injection_Equipment','Extraction_Method', 'Operating_Time', 'Packaging_Method', 'Date_of_Preparation',
            'Specification', 'Raw_Materials_Used', 'Process_Name', 'Remarks_1', 'Remarks_2', 'Remarks_3', 'Remarks_4', 'Remarks_5',
            'General_Management_Rate', 'Defective_Rate', 'Profit_Rate', 'Other', 'Final_Estimate', 'Supplier_Estimate' ] 
        widgets = {             
            'Date_of_Preparation': forms.TextInput(attrs={'type': 'date'}), 
            'Remarks_1': forms.Textarea(attrs={'rows': 3}),
            'Remarks_2': forms.Textarea(attrs={'rows': 3}),
            'Remarks_3': forms.Textarea(attrs={'rows': 3}),
            'Remarks_4': forms.Textarea(attrs={'rows': 3}),
            'Remarks_5': forms.Textarea(attrs={'rows': 3}),
            'Other': forms.Textarea(attrs={'rows': 4}),
            'General_Management_Rate': forms.NumberInput(attrs={'class': 'small-width'}),
            'Defective_Rate': forms.NumberInput(attrs={'class': 'small-width'}),
            'Profit_Rate': forms.NumberInput(attrs={'class': 'small-width'}),
        }
    
