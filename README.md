# HELLO APP


.github/workflow/actions.yml

```yml
name: Actions

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
    - uses: actions/checkout@v2

    - name: Set up JDK 11
      uses: actions/setup-java@v2
      with:
        java-version: '11'
        distribution: 'adopt'

    # https://docs.github.com/en/actions/learn-github-actions/workflow-commands-for-github-actions#setting-an-environment-variable
    - name: application build and push
      run: |
        gradle clean jib --image youthconpso/hello:${{ github.run_id }} \
        -Djib.to.auth.username=${{ secrets.DUSER }} \
        -Djib.to.auth.password=${{ secrets.DPWD }}

  deploy:
    needs: build
    runs-on: ubuntu-latest
    
    steps:
    # https://github.com/marketplace/actions/setup-kustomize
    - name: setup kustomize
      uses: imranismail/setup-kustomize@v1

    # https://github.com/actions/checkout
    - name: checkout config repo
      uses: actions/checkout@v2
      with: 
        repository: sang5c/demo-youthcon-21-config # TODO: 본인의 repository 변경 -> {username}/{repository}
        token: ${{ secrets.ACCESS_TOKEN }}
        path: demo-youthcon-21-config
    
    - name: run kustomize
      # TODO: youthconpso를 본인의 dockerhub 경로로 변경
      run: |
        cd demo-youthcon-21-config/overlays/sample/
        kustomize edit set image youthconpso/hello=youthconpso/hello:${{ github.run_id }}
        cat kustomization.yml
    
    - name: config commit and push
      run: |
        cd demo-youthcon-21-config
        git config user.name github-actions
        git config user.email github-actions@github.com
        git add .
        git commit -m "update image"
        git push
```
