// @ts-ignore
/* eslint-disable */
import request from '@/request'

/** 此处后端没有提供注释 GET /health/health-check */
export async function healthCheck(options?: { [key: string]: any }) {
  return request<API.BaseResponseObject>('/health/health-check', {
    method: 'GET',
    ...(options || {}),
  })
}
