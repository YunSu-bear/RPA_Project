from django.urls import path
from . import views

urlpatterns = [
    path('', views.home, name='home'), # 메인 페이지 경로
    path('input_page/', views.input_view, name='input_page'), # 저장 페이지 경로
    path('delete_page/', views.delete_view, name='delete_page'), # 삭제 페이지 경로
    path('detail/', views.detail_view, name='detail_page'), # 견적서 페이지 경로
    path('view_index/', views.view_index, name='view_index'),
    path('excel_view/', views.excel_view, name='excel_page'),
    path('export/', views.export, name='export' )
]

